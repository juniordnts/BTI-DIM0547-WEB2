package com.imd.project.service;

import java.util.List;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;
import com.github.underscore.U;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.imd.project.model.Customer;
import com.imd.project.model.Payment;
import com.imd.project.repository.PaymentRepository;

@Component
public class PaymentServiceImpl implements PaymentService {

  @Autowired
  PaymentRepository modelEntityRepository;

  @Value("${payment.mercadopago.token}")
  private String mercadoPagoToken;

  @Override
  public Payment save(Payment modelInstance, Customer customerFound) {

    try {
      URL url = new URL("https://api.mercadopago.com/v1/payments");
      HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
      httpConn.setRequestMethod("POST");

      httpConn.setRequestProperty("Authorization", "Bearer ".concat(mercadoPagoToken));
      httpConn.setRequestProperty("Content-Type", "application/json");

      httpConn.setDoOutput(true);

      String paymentObject = U.objectBuilder()
          .add("transaction_amount", modelInstance.getTotal())
          .add("description", "Pagamento de procedimentos")
          .add("payment_method_id", "pix")
          .add("statement_descriptor", "WEB2")
          .add("payer", U.objectBuilder()
              .add("email", customerFound.getEmail())
              .add("first_name", customerFound.getName())
              .add("last_name", customerFound.getName())
              .add("identification", U.objectBuilder()
                  .add("type", "CPF")
                  .add("number", customerFound.getCpf())))
          .toJson();

      OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
      writer.write(paymentObject);
      writer.flush();
      writer.close();

      httpConn.getOutputStream().close();

      InputStream responseStream = httpConn.getResponseCode() / 100 == 2
          ? httpConn.getInputStream()
          : httpConn.getErrorStream();

      Scanner scan = new Scanner(responseStream).useDelimiter("\\A");
      String response = scan.hasNext() ? scan.next() : "";

      JSONObject responseJava = new JSONObject(response);

      String resQr = responseJava.getJSONObject("point_of_interaction").getJSONObject("transaction_data")
          .getString("qr_code_base64");
      String resCode = String.valueOf(responseJava.getInt("id"));
      String resStatus = responseJava.getString("status");

      modelInstance.setQr(resQr);
      modelInstance.setCode(resCode);
      modelInstance.setStatus(resStatus);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return modelEntityRepository.save(modelInstance);
  }

  @Override
  public void delete(Payment modelInstance) {
    modelEntityRepository.delete(modelInstance);
  }

  @Override
  public Payment getOneById(Integer id) {
    return modelEntityRepository.findById(id).map(modelInstance -> {
      return modelInstance;
    }).orElseThrow(() -> null);
  }

  @Override
  public List<Payment> getAll() {
    return modelEntityRepository.findAll();
  }

}
