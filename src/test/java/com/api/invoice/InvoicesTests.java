package com.api.invoice;
import com.api.invoice.exceptions.InvoiceNotFoundException;
import com.api.invoice.models.Invoice;
import com.api.invoice.models.Line;
import com.api.invoice.models.Vendor;
import com.api.invoice.services.InvoiceServiceClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Vector;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class InvoicesTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InvoiceServiceClass invoiceService;

    @Test
    public void shouldReturnInvoices() throws Exception{
        this.mvc.perform(get("/invoices/"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnInvoice() throws Exception{
        Invoice invoice = new Invoice();
        invoice.setSubtotal(100.00);
        Vendor vendor = new Vendor("name", "String address", "String phoneNumber", "String email",  "VATNumber");
        invoice.setVendor(vendor);
        Vector<Line> lines = new Vector<Line>();
        lines.add(new Line("desc",200.00,200, 21));
        invoice.setLines(lines);
        ObjectMapper objectMapper = new ObjectMapper();
        given(invoiceService.getInvoice(invoice.getId())).willReturn(invoice);

        this.mvc.perform(get("/invoices/get/" + invoice.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(invoice)));
    }

    @Test
    public void shouldReturn404_Invoice() throws Exception{
        Invoice invoice = new Invoice();
        invoice.setSubtotal(100.00);
        given(invoiceService.getInvoice(invoice.getId())).willThrow(new InvoiceNotFoundException("Invoice not found"));

        this.mvc.perform(get("/invoices/get/" + invoice.getId()))
                .andExpect(status().is(404));
    }

    @Test
    public void shouldUploadInvioce() throws Exception{
        Invoice invoice = new Invoice();
        invoice.setSubtotal(200.00);
        MockMultipartFile image = new MockMultipartFile("image","image".getBytes());
        given(invoiceService.uploadInvoice(image, "eng")).willReturn(invoice);

        this.mvc.perform(post("/invoices/upload/").contentType(MediaType.APPLICATION_JSON).content("{\"language\":\"eng\",\"image\":\"ez\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteInvoice() throws Exception{
        Invoice invoice = new Invoice();
        invoice.setSubtotal(100.00);
        this.mvc.perform(delete("/invoices/delete/" + invoice.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateInvoice() throws Exception{
        Invoice invoice = new Invoice();
        invoice.setSubtotal(400.00);
        ObjectMapper objectMapper = new ObjectMapper();
        given(invoiceService.updateInvoice(invoice.getId(),invoice)).willReturn(invoice);

        this.mvc.perform(put("/invoices/update/" + invoice.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new Invoice())))
                .andExpect(status().isOk());
    }


}
