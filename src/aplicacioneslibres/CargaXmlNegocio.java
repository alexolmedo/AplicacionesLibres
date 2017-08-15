/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import Interfaces.SeleccionarTipoGastoNegocio;
import Interfaces.SeleccionarTipoGastoNegocios1;
import Interfaces.SeleccionarTipoGastoPersonal;
import conexionBDD.Conexionn;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author root
 */
public class CargaXmlNegocio {

    public void cargarXml(String name, String cedulaCli, int anio, String tipo) throws IOException {
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(name);

        //Reformatear facturas de Payless
        try {
            FileReader fr = new FileReader(xmlFile);
            String s;
            String totalStr = "";
            try (BufferedReader br = new BufferedReader(fr)) {
                while ((s = br.readLine()) != null) {
                    totalStr += s;
                }
                totalStr = totalStr.replaceAll("<RespuestaAutorizacionComprobante>", "");
                totalStr = totalStr.replaceAll("<autorizaciones>", "");
                totalStr = totalStr.replaceAll("</ambiente>    </autorizacion>", "</ambiente>");
                totalStr = totalStr.replaceAll("</autorizaciones></RespuestaAutorizacionComprobante>", "</autorizacion>");

                FileWriter fw = new FileWriter(xmlFile);
                fw.write(totalStr);
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);
            Conexionn cp = new Conexionn();

            ArrayList elementos = new ArrayList();

            int cont;
            elementos.add("estado");
            elementos.add("ambiente");
            elementos.add("razonSocial");
            elementos.add("dirMatriz");
            elementos.add("ruc");
            elementos.add("estab");
            elementos.add("ptoEmi");
            elementos.add("secuencial");
            elementos.add("fechaEmision");
            elementos.add("razonSocialComprador");
            elementos.add("identificacionComprador");
            elementos.add("totalSinImpuestos");
            elementos.add("valor");
            elementos.add("descripcion");
            elementos.add("precioTotalSinImpuesto");
            elementos.add("importeTotal");

            //Se obtiene la raiz 'tables'
            Element rootNode = document.getRootElement();

            // Datos sin cabecera
            cont = elementos.indexOf("estado");
            String estado = "";
            if (cont != -1) {
                Element est = (Element) rootNode.getChild(elementos.get(cont).toString());
                if (est != null) {
                    estado = est.getTextTrim();
                }
            }

            cont = elementos.indexOf("ambiente");
            String ambiente = "";
            if (cont != -1) {
                Element amb = (Element) rootNode.getChild(elementos.get(cont).toString());
                if (amb != null) {
                    ambiente = amb.getTextTrim();
                }
            }

            Element tabla = rootNode.getChild("comprobante");

            if (tabla != null) {
                String ex = tabla.getText();

                InputStream stream = new ByteArrayInputStream(ex.getBytes("UTF-8"));
                Document parse = builder.build(stream);

                tabla = parse.getRootElement();
            } else {
                tabla = rootNode;
            }

            String fechaCompleta = tabla.getChildren().get(1).getChildTextTrim("fechaEmision");
            System.out.println(fechaCompleta);
            StringTokenizer tk = new StringTokenizer(fechaCompleta, "/");
            String verificarFecha = "";

            while (tk.hasMoreTokens()) {
                verificarFecha = tk.nextToken();
            }
            //System.out.println(verificarFecha);

            if (verificarFecha.equals(String.valueOf(anio))) {
                List lista_campos = tabla.getChildren();
                Element campo;

                Element tributaria = (Element) lista_campos.get(0);

                // Info Tributaria
                cont = elementos.indexOf("razonSocial");
                String nombreEst = "";
                if (cont != -1) {
                    nombreEst = tributaria.getChildTextTrim(elementos.get(cont).toString());
                }

                cont = elementos.indexOf("dirMatriz");
                String dirMatriz = "";
                if (cont != -1) {
                    dirMatriz = tributaria.getChildTextTrim(elementos.get(cont).toString());
                }

                cont = elementos.indexOf("ruc");
                String ruc = "";
                if (cont != -1) {
                    ruc = tributaria.getChildTextTrim(elementos.get(cont).toString());
                }

                cont = elementos.indexOf("estab");
                String estab = "";
                if (cont != -1) {
                    estab = tributaria.getChildTextTrim(elementos.get(cont).toString());
                }

                cont = elementos.indexOf("ptoEmi");
                String emision = "";
                if (cont != -1) {
                    emision = tributaria.getChildTextTrim(elementos.get(cont).toString());
                }

                cont = elementos.indexOf("secuencial");
                String secuencial = "";
                if (cont != -1) {
                    secuencial = tributaria.getChildTextTrim(elementos.get(cont).toString());
                }

                String numFact = estab + "-" + emision + "-" + secuencial;

                //Se obtiene la raiz de la factura
                Element factura = (Element) lista_campos.get(1);

                // Info Factura
                cont = elementos.indexOf("fechaEmision");

                String fecha = "";
                if (cont != -1) {
                    String fechaA = "";
                    fechaA = factura.getChildTextTrim(elementos.get(cont).toString());
                    StringTokenizer tok = new StringTokenizer(fechaA, "/");
                    String d = tok.nextToken();
                    String m = tok.nextToken();
                    String a = tok.nextToken();
                    fecha = m + "/" + d + "/" + a;
                }

                //System.out.println(fecha);
                cont = elementos.indexOf("razonSocialComprador");
                String nombreCompr = "";
                if (cont != -1) {
                    nombreCompr = factura.getChildTextTrim(elementos.get(cont).toString());
                }
                //System.out.println(nombreCompr);

                cont = elementos.indexOf("identificacionComprador");
                String CI_Compr = "";
                if (cont != -1) {
                    CI_Compr = factura.getChildTextTrim(elementos.get(cont).toString());
                }
                //System.out.println(CI_Compr);

                cont = elementos.indexOf("totalSinImpuestos");
                Double totalSinImp = 0.0;
                if (cont != -1) {
                    totalSinImp = Double.parseDouble(factura.getChildTextTrim(elementos.get(cont).toString()));
                }

                List totalConImp = factura.getChild("totalConImpuestos").getChildren();
                Element totalImp = (Element) totalConImp.get(0);

                /*cont = elementos.indexOf("valor");
                Double Imps = 0.0;
                if (cont != -1) {
                    Imps = Double.parseDouble(totalImp.getChildTextTrim(elementos.get(cont).toString()));
                    System.err.println(Imps);
                }*/
                cont = elementos.indexOf("importeTotal");
                Double totalConIVA = 0.0;
                if (cont != -1) {
                    totalConIVA = Double.parseDouble(factura.getChildTextTrim(elementos.get(cont).toString()));
                }

                BigDecimal Imps = new BigDecimal(totalConIVA).subtract(new BigDecimal(totalSinImp));
                BigDecimal impuesto = Imps.setScale(2, RoundingMode.HALF_EVEN);

                if (CI_Compr.equalsIgnoreCase(cedulaCli)) {
                    if (!cp.verificar_usuario("SELECT * FROM FACTURA WHERE id_factura='" + numFact + "'")) {

                        //Inserta establecimiento en caso de no existir
                        System.out.println("select * from prov_gasto where tipo_fac='Negocio' and proveedor='" + ruc + "'");
                        if (!cp.verificar_usuario("select * from prov_gasto where tipo_fac='Negocio' and proveedor='" + ruc + "'")) {

                            Object seleccion = JOptionPane.showInputDialog(null, "Se ha detectado el proveedor\n" + nombreEst + "\nSeleccione el tipo de gasto principal:", "Nuevo Proveedor Detectado",
                                    JOptionPane.QUESTION_MESSAGE, null, // null para icono defecto
                                    cp.cargarTipoGasNegocio(cedulaCli).toArray(), "Seleccione un tipo de Gasto");

                            String TipoGasto_Es = "Insert into Prov_gasto (tipo_fac, proveedor,tipo_gasto) "
                                    + "VALUES ('Negocio','" + ruc + "','" + seleccion.toString() + "')";
                            cp.insertar(TipoGasto_Es);
                        }

                        if (!cp.verificar_usuario("SELECT * FROM ESTABLECIMIENTO WHERE id_establecimiento='" + ruc + "'")) {

                            System.out.println("Estamos en el if");
                            String establecimiento = "INSERT INTO ESTABLECIMIENTO (id_establecimiento,nombre_establecimiento,direccion_establecimiento)"
                                    + "VALUES ('" + ruc + "','" + nombreEst + "','" + dirMatriz + "')";
                            cp.insertar(establecimiento);
                        }

                        String facturaQ = "INSERT INTO FACTURA (id_factura,id_cliente,id_establecimiento,tipo_factura,fecha_emision,estado_factura,ambiente_factura,total_sin_iva,iva,total_con_iva)"
                                + "VALUES ('" + numFact + "','" + CI_Compr + "','" + ruc + "','" + tipo + "','" + fecha + "','" + estado + "','" + ambiente + "'," + totalSinImp + "," + impuesto + "," + totalConIVA + ")";
                        System.out.println(facturaQ);
                        cp.insertar(facturaQ);

                        Element detalles = (Element) lista_campos.get(2);
                        List detalle = detalles.getChildren();

                        Object datosProducto[][] = new Object[detalle.size()][3];

                        for (int j = 0; j < detalle.size(); j++) {

                            campo = (Element) detalle.get(j);

                            // Detalle
                            cont = elementos.indexOf("descripcion");
                            String descripcion = "";
                            if (cont != -1) {
                                descripcion = campo.getChildTextTrim(elementos.get(cont).toString());
                            }

                            cont = elementos.indexOf("precioTotalSinImpuesto");
                            Double total = 0.0;
                            if (cont != -1) {
                                total = Double.parseDouble(campo.getChildTextTrim(elementos.get(cont).toString()));
                            }

                            if (!descripcion.equals("")) {
                                datosProducto[j][0] = descripcion;
                                datosProducto[j][1] = total;
                                datosProducto[j][2] = "";
                            }
                        }

                        if (datosProducto.length != 0) {
                            if (tipo.equals("Personal")) {

                                SeleccionarTipoGastoPersonal seleccionarP = new SeleccionarTipoGastoPersonal(cp, datosProducto, numFact, anio,
                                        cedulaCli, tipo, nombreCompr, CI_Compr, ruc, nombreEst, dirMatriz, numFact, fechaCompleta,
                                        totalSinImp.toString(), impuesto.toString(), totalConIVA.toString());
                                seleccionarP.setVisible(true);

                            } else {

                                SeleccionarTipoGastoNegocio seleccionarH = new SeleccionarTipoGastoNegocio(cp, datosProducto, numFact, anio,
                                        cedulaCli, tipo, nombreCompr, CI_Compr, ruc, nombreEst, dirMatriz, numFact, fechaCompleta,
                                        totalSinImp.toString(), impuesto.toString(), totalConIVA.toString());
                                seleccionarH.setVisible(true);
                                //SeleccionarTipoGastoNegocios1 seleccionarH = new SeleccionarTipoGastoNegocios1(cp, datosProducto, numFact, anio, cedulaCli, tipo);
                                //seleccionarH.setVisible(true);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Esta factura ya fue ingresada");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La identificación en la factura no corresponde al usuario actual");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El año de la factura no corresponde con el año seleccionado");
            }
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
    }
}
