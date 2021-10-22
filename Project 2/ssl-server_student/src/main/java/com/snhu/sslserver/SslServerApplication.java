package com.snhu.sslserver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{    
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "My Name is Eric Boilard, Hello World Check Sum!";
    	String checksum;
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	byte[] hash = md.digest(data.getBytes(StandardCharsets.UTF_8));
    	checksum = bytesToHex(hash);
       
        return "<p>data:"+data+"\n"+"<p>Name of Cipher Algorithm Used: SHA-256 Checksum Value:"+checksum;
    }

    private static String bytesToHex(byte[] hash) {
    	StringBuilder hexString = new StringBuilder(2 * hash.length);
    	for (int i = 0; i < hash.length; i++) {
    		String hex = Integer.toHexString(0xff & hash[i]);
    		if(hex.length()==1)
    			hexString.append('0');
    		hexString.append(hex);
		}
	return hexString.toString();

    }
    	
}