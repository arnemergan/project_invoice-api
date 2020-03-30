package com.api.invoice.services;
import com.api.invoice.models.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.IOException;

public interface InvoiceService {
    public Invoice getInvoice(String id);
    public Page<Invoice> getInvoices(Pageable pageable);
    public Invoice uploadInvoice(MultipartFile image, String lang) throws IOException;
    public Invoice updateInvoice(String id, Invoice invoice);
    public void deleteInvoice(String id);
}
