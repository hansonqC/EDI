package pl.hansonq.utils;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import pl.hansonq.models.CartBpModel;
import pl.hansonq.models.DocumentInvoiceModel;
import pl.hansonq.models.PSBModel.TestModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxB {

    final static Logger log = Logger.getLogger(JaxB.class);
    public JaxB() {

        DOMConfigurator.configure("log4j.xml");
    }

    public static void jaxbObjectToCommavalXML(CartBpModel cartBpModel, String file){
        try {
            JAXBContext context = JAXBContext.newInstance(CartBpModel.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
           // m.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
         //   m.setProperty("com.sun.xml.bind.xmlHeaders","<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

            // Write to System.out for debugging
            m.marshal(cartBpModel, System.out);

            // Write to File
            m.marshal(cartBpModel, new File(file));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

//    public static void jaxbObjectToMkplXML(KatalogModel katalogModel, String file){
//        try {
//            JAXBContext context = JAXBContext.newInstance(KatalogModel.class);
//            Marshaller m = context.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//            m.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//            // m.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            //   m.setProperty("com.sun.xml.bind.xmlHeaders","<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//
//            // Write to System.out for debugging
//            m.marshal(katalogModel, System.out);
//
//            // Write to File
//            //   m.marshal(katalogModel, new File(file));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void jaxbObjectToO4oXML(KatalogModelO4O katalogModelO4O, String file){
//        try {
//            JAXBContext context = JAXBContext.newInstance(KatalogModelO4O.class);
//            Marshaller m = context.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//            m.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//            // m.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            //   m.setProperty("com.sun.xml.bind.xmlHeaders","<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//
//            // Write to System.out for debugging
//            m.marshal(katalogModelO4O, System.out);
//
//            // Write to File
//               m.marshal(katalogModelO4O, new File(file));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }
//

    public static TestModel jaxbTestModelXMLToObject(String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(TestModel.class);
            Unmarshaller un = context.createUnmarshaller();
            TestModel testModel = (TestModel) un.unmarshal(new File(file));
            //  System.out.println(cartBpModel.toString());
            return testModel;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static CartBpModel jaxbCartBpModelXMLToObject(String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(CartBpModel.class);
            Unmarshaller un = context.createUnmarshaller();
            CartBpModel cartBpModel = (CartBpModel) un.unmarshal(new File(file));
          //  System.out.println(cartBpModel.toString());
            return cartBpModel;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static DocumentInvoiceModel jaxbXMLToDocumentInvoiceModelObject(String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(DocumentInvoiceModel.class);
            Unmarshaller un = context.createUnmarshaller();
            DocumentInvoiceModel documentInvoiceModel = (DocumentInvoiceModel) un.unmarshal(new File(file));
              System.out.println(documentInvoiceModel.toString());
            return documentInvoiceModel;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }



//    public static KatalogModel jaxbKatalogXMLToObject(String file) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(KatalogModel.class);
//            Unmarshaller un = context.createUnmarshaller();
//            KatalogModel katalogModel = (KatalogModel) un.unmarshal(new File(file));
//            return katalogModel;
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static KatalogModelO4O jaxbKatalogO4oXMLToObject(String file) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(KatalogModelO4O.class);
//            Unmarshaller un = context.createUnmarshaller();
//            KatalogModelO4O katalogModelO4O = (KatalogModelO4O) un.unmarshal(new File(file));
//            return katalogModelO4O;
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//

}
