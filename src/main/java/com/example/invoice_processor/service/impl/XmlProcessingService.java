package com.example.invoice_processor.service.impl;

import com.example.invoice_processor.entity.InvoiceEntity;
import com.example.invoice_processor.exception.XmlValidationException;
import com.example.invoice_processor.generated.Faktura;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;

@Service
public class XmlProcessingService {

    public InvoiceEntity processXml(String base64Xml) {

        String xmlContent = decodeBase64(base64Xml);


        validateXml(xmlContent);


        Faktura faktura = unmarshalXml(xmlContent);


        return extractInvoiceEntity(faktura);
    }

    private String decodeBase64(String base64Xml) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Xml);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            throw new XmlValidationException("Invalid Base64 encoding", e);
        }
    }

    private void validateXml(String xmlContent) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            StreamSource xsdSource = new StreamSource(new ClassPathResource("xsd/faktura.xsd").getInputStream());
            Schema schema = schemaFactory.newSchema(xsdSource);

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xmlContent)));
        } catch (SAXException | IOException e) {
            throw new XmlValidationException("XML validation failed: " + e.getMessage(), e);
        }
    }

    private Faktura unmarshalXml(String xmlContent) {
        try {
            JAXBContext context = JAXBContext.newInstance(Faktura.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Faktura) unmarshaller.unmarshal(new StringReader(xmlContent));
        } catch (JAXBException e) {
            throw new XmlValidationException("XML parsing failed: " + e.getMessage(), e);
        }
    }

    private InvoiceEntity extractInvoiceEntity(Faktura faktura) {
        InvoiceEntity entity = new InvoiceEntity();


        if (faktura.getPodmiot1() != null &&
                faktura.getPodmiot1().getDaneIdentyfikacyjne() != null) {
            String nip = faktura.getPodmiot1().getDaneIdentyfikacyjne().getNIP();
            entity.setNip(nip);
        }


        if (faktura.getFa() != null && faktura.getFa().getP1() != null) {
            XMLGregorianCalendar p1Xml = faktura.getFa().getP1();
            LocalDate p1 = p1Xml.toGregorianCalendar().toZonedDateTime().toLocalDate();
            entity.setP1(p1);
        }


        if (faktura.getFa() != null && faktura.getFa().getP2() != null) {
            String p2 = faktura.getFa().getP2();
            entity.setP2(p2);
        }

        return entity;
    }


    private LocalDate convertToLocalDate(XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar == null) {
            return null;
        }
        return xmlGregorianCalendar.toGregorianCalendar()
                .toZonedDateTime()
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDate();
    }
}