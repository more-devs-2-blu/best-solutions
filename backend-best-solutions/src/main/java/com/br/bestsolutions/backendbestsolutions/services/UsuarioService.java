package com.br.bestsolutions.backendbestsolutions.services;

import com.br.bestsolutions.backendbestsolutions.dtos.UsuarioDto;
import com.br.bestsolutions.backendbestsolutions.models.Usuario;
import com.br.bestsolutions.backendbestsolutions.repositories.UsuarioRepository;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void register(Usuario usuario) {
        usuarioRepository.save(usuario);


    }

    @Autowired
    private JavaMailSender emailSender;

    public Usuario enviarEmail(Usuario solutionsModelo) {
        String entradaMail = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setText("Olá "+solutionsModelo.getResponsavel()+ ", a sua solicatação para abertura da empresa: "+ solutionsModelo.getNomeFantasia()+
                    " foi recebida em "+ entradaMail + ". \nNo momento, a situação cadastral é: Em análise"+
                    ". Você será informado assim que houver novas atualizações.");
            message.setTo(solutionsModelo.getEmail());
            message.setFrom("bestsolutionsdevs@gmail.com");
            message.setSubject("Abertura de empresa");

            emailSender.send(message);


        }catch (MailException e){

        }finally {

            return usuarioRepository.save(solutionsModelo);
        }
    }
    public void enviarEmailAnexo(String toEmail,
                                 String body,
                                 String subject,
                                 String attachment) throws MessagingException {
        MimeMessage mimeMessage=emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("bestsolutionsdevs@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource=
                new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
                fileSystemResource);
        emailSender.send(mimeMessage);
        System.out.printf("Email com anexo enviado com sucesso.");

    }
    public void gerarPdf(String Responsavel,String RazaoSocial, String NomeFantasia, String Email, String NaturezaJuridica, String Enquadramento,
                         String Cep, String Estado, String Cidade, String Bairro, String Endereco, int Numero, String Cnae, float AreaTotal) throws IOException {
        String entradaPdf = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String imgSrc = "Recursos//logo.jfif";
        ImageData data = ImageDataFactory.create(imgSrc);
        Image image1 = new Image(data);
        image1.scaleToFit(170,170);
        image1.setMarginLeft(200);
        String path = "Recursos//Cadastro.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);
        document.setMargins(0,10,0,0);
        PdfFont fontLabel = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont fontTitulo = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLDOBLIQUE);

        Paragraph p0 = new Paragraph("Cadastro de Empresa").setFont(fontTitulo).setFontColor(Color.BLACK).setFontSize(16).setTextAlignment(TextAlignment.CENTER).setMarginBottom(20);
        Paragraph p1 = new Paragraph("Responsável").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50);
        Paragraph p2 = new Paragraph(Responsavel).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p3 = new Paragraph("Nome Fantasia").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p4 = new Paragraph(NomeFantasia).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p5 = new Paragraph("Natureza Jurídica").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p6 = new Paragraph(NaturezaJuridica).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p7 = new Paragraph("Cep").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p8 = new Paragraph(Cep).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p9 = new Paragraph("Cidade").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p10 = new Paragraph(Cidade).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p11 = new Paragraph("Endereco").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p12 = new Paragraph(String.valueOf(Endereco)).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p13 = new Paragraph("Cnae").setFont(fontLabel).setFontColor(Color.GRAY).setFontSize(12).setMarginLeft(50).setMarginTop(30);
        Paragraph p14 = new Paragraph(String.valueOf(Cnae)).setFont(font).setMarginTop(0).setMarginLeft(50);
        Paragraph p15 = new Paragraph("Documento gerado em: "+ entradaPdf).setFont(font).setMarginTop(0).setMarginLeft(50).setMarginTop(30);


        p1.add(new Tab());
        p1.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p1.add("Razão Social").setFont(font).setFontColor(Color.GRAY);
        p2.add(new Tab());
        p2.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p2.add(RazaoSocial).setFont(font);
        p3.add(new Tab());
        p3.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p3.add("Email").setFont(font).setFontColor(Color.GRAY);
        p4.add(new Tab());
        p4.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p4.add(Email).setFont(font);
        p5.add(new Tab());
        p5.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p5.add("Enquadramento").setFont(font).setFontColor(Color.GRAY);
        p6.add(new Tab());
        p6.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p6.add(Enquadramento).setFont(font);
        p7.add(new Tab());
        p7.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p7.add("Estado").setFont(font).setFontColor(Color.GRAY);
        p8.add(new Tab());
        p8.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p8.add(Estado).setFont(font);
        p9.add(new Tab());
        p9.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p9.add("Bairro").setFont(font).setFontColor(Color.GRAY);
        p10.add(new Tab());
        p10.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p10.add(Bairro).setFont(font);
        p11.add(new Tab());
        p11.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p11.add("Número").setFont(font).setFontColor(Color.GRAY);
        p12.add(new Tab());
        p12.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p12.add(String.valueOf(Numero)).setFont(font);
        p13.add(new Tab());
        p13.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p13.add("Área Total").setFont(font).setFontColor(Color.GRAY);
        p14.add(new Tab());
        p14.addTabStops(new TabStop(1000, TabAlignment.RIGHT)).setMarginRight(50);
        p14.add(String.valueOf(AreaTotal+ " m²")).setFont(font);
        document.add(image1);
        document.add(p0);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(p5);
        document.add(p6);
        document.add(p7);
        document.add(p8);
        document.add(p9);
        document.add(p10);
        document.add(p11);
        document.add(p12);
        document.add(p13);
        document.add(p14);
        document.add(p15);
        document.close();


    }

    public Usuario save(Usuario solutionsModelo) {
        return usuarioRepository.save(solutionsModelo);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    public void delete(Usuario solutionsModelo) {
        usuarioRepository.delete(solutionsModelo);
    }
}
