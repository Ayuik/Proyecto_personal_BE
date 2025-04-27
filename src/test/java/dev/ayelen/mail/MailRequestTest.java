package dev.ayelen.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MailRequestTest {
    
    @Test
    void testGettersAndSetters(){
        MailRequest mailRequest = new MailRequest();
        mailRequest.setName("aye");
        mailRequest.setEmail("aye@mail.com");
        mailRequest.setSubject("test");
        mailRequest.setMessage("testing mail");
        assertEquals(mailRequest.getName(), "aye");
        assertEquals(mailRequest.getEmail(), "aye@mail.com");
        assertEquals(mailRequest.getSubject(), "test");
        assertEquals(mailRequest.getMessage(), "testing mail"); 
    }
}
