package de.friqql.pdf;

/*
    Author     : Andreas Hellrich
 */
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.friqql.model.POrder;
import de.friqql.controller.OrderController;
import de.friqql.controller.UsrController;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Das Servlet zur PDF-Erstellung
 *
 * @author Andreas Hellrich
 */
@WebServlet(name = "myPdf", urlPatterns = "/rechnung")
public class Rechnung extends HttpServlet implements Serializable{
@Inject OrderController orderController;
@Inject UsrController usrController;
    /**
     * Erstellt eine PDF
     *
     * @param req
     * @param resp
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //******************************** Variablen ******************************
        //sessionzeug
        
 
        

// Document
        Document document = new Document();

        // Output Stream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // Tabellen
        PdfPTable tableSession;
        PdfPTable tableHinweis;
        PdfPTable tableFood;
        PdfPTable tableSumme;

        // Bean
        OrderController orderBean = orderController;
        UsrController userBean = usrController;
        // Formatierung
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font semiBoldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font smallFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
        NumberFormat f = DecimalFormat.getCurrencyInstance(Locale.FRANCE);

        // Chunks und Phrasen
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        //****************************** Erstellung**************************************
        try {
            resp.setContentType("application/pdf");

           
            if (!usrController.getMyUsr().getUTitle().isEmpty()) {
                
                document = new Document();
                bos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, bos);

                document.open();
                // Adresse
                document.add(new Paragraph(userBean.getMyUsr().getUTitle() + " " + userBean.getMyUsr().getUFirstname() + " " + userBean.getMyUsr().getULastname()));
                document.add(new Paragraph(userBean.getMyUsr().getUStreet() + " " + userBean.getMyUsr().getUHouse()));
                document.add(new Paragraph(userBean.getMyUsr().getUPlz() + " " + userBean.getMyUsr().getUPlace()));

                // Überschrift
                document.add(new Paragraph("Rechnung", boldFont));
                document.add(new Paragraph("\n", boldFont));

                // Rechnungsdatum
                document.add(new Paragraph("Datum: " + dateFormat.format(date), semiBoldFont));
                document.add(new Paragraph("\n", semiBoldFont));

                // Anlegen der Tabellen
                tableHinweis = new PdfPTable(1);
                tableSession = new PdfPTable(2);
                tableSumme = new PdfPTable(2);
                tableFood = new PdfPTable(8);

                // Füllen der Tabellen
                
                List<POrder> ol =  orderBean.getOrderL();
                for (POrder tmp :  ol) {

                    if (tmp != null) {
                        double mul = tmp.getOAmmount() * tmp.getOPrice();

                        tableFood.addCell(new PdfPCell(new Phrase("Speise", smallFont)));
                        tableFood.addCell(new PdfPCell(new Phrase(tmp.getOName(), smallFont)));
                        tableFood.addCell(new PdfPCell(new Phrase("Anzahl", smallFont)));
                        tableFood.addCell(new PdfPCell(new Phrase(String.valueOf(tmp.getOAmmount()), smallFont)));
                        tableFood.addCell(new PdfPCell(new Phrase("Einzelpreis", smallFont)));
                        tableFood.addCell(tmp.getOPrice() + "0€ ");
                        tableFood.addCell(new PdfPCell(new Phrase("Zusammen", smallFont)));
                        tableFood.addCell(mul + "0€ ");
                    }
                }
                tableSumme.addCell("Gesammtsumme: ");
                tableSumme.addCell(new Phrase(String.valueOf(f.format(orderBean.getSum())), smallFont));
               
                // Hinzufügen der Tabellen zum Dokument
                document.add(new Paragraph("\n"));
                document.add(tableFood);

                document.add(tableSumme);
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("\n"));
                document.add(tableHinweis);

                document.add(tableSession);
                document.newPage();

            } else { //Wenn keine aktuelle Session vorhanden ist
                PdfWriter.getInstance(document, bos);
                document.open();

                document.add(new Paragraph("Session abgelaufen  "));
            }
            document.close();
            try (OutputStream os = resp.getOutputStream()) {
                bos.writeTo(os);
                os.flush();

            }

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(Rechnung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}

/**
 *
 * @author Teilnehmer
 */
