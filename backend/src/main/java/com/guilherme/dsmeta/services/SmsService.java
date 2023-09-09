package com.guilherme.dsmeta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.guilherme.dsmeta.entities.Sale;
import com.guilherme.dsmeta.repositories.SalesRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {
	
	@Autowired
	private SalesRepository saleRepository;

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	public void sendSms(Long saleId) {
		
		Sale sale = this.saleRepository.findById(saleId).get();
		
		String date = sale.getDate().getMonthValue() +"/"+ sale.getDate().getYear();
		
		String mensagem = "O vendedor " + sale.getSellerName() 
			+ " foi destaque em " + date 
			+ " com um total de R$ " + String.format("%.2f", sale.getAmount());

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, mensagem).create();

		System.out.println(message.getSid());
	}
}
