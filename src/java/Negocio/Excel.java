/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jefersonrr
 */
public class Excel {

   private static final Logger LOGGER = Logger.getLogger("mx.com.hash.newexcel.ExcelOOXML");

   //EJEMPLO DE COMO CREAR EL JSON Y LLAMAR AL METODO
   /*public static void main(String[] args) {
        long cedula = 1010127350545454545l;
        double ganancias = 20000.56;
        JSONArray arrayJon = new JSONArray();
        JSONObject json = new JSONObject();
        String[] encabezado = {"nombre", "apellido", "cedula", "ganancias"};
        for (int i = 0; i < 10; i++) {

            json.put("nombre", "Jeferson");
            json.put("apellido", "Rodriguez Ramirez");
            json.put("cedula", cedula);
            json.put("ganancias", ganancias);
            arrayJon.add(json);
        }
        crearExcel(arrayJon, encabezado,"reporte.xlsx");
    }*/
    public static void crearExcel(JSONArray jsonArray, String[] encabezado, String ruta) {
     
        File archivo = new File(ruta); 
        Workbook workbook = new XSSFWorkbook();
        Sheet pagina = workbook.createSheet("Informe");
        CellStyle style = workbook.createCellStyle();    
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short) 12);
        headerStyle.setFont(font);
        Row fila = pagina.createRow(0);

        for (int i = 0; i < encabezado.length; i++) {
            Cell celda = fila.createCell(i);
            celda.setCellStyle(style);
            celda.setCellStyle(headerStyle);
            celda.setCellValue(encabezado[i]);
            
        }

        CellStyle datosEstilo = workbook.createCellStyle();
        datosEstilo.setBorderBottom(BorderStyle.THIN);
        datosEstilo.setBorderLeft(BorderStyle.THIN);
        datosEstilo.setBorderRight(BorderStyle.THIN);
        datosEstilo.setBorderBottom(BorderStyle.THIN);

        crearCeldas(jsonArray, encabezado, fila, pagina, datosEstilo);
        try {

            FileOutputStream salida = new FileOutputStream(archivo);
            workbook.write(salida);
            workbook.close();

            LOGGER.log(Level.INFO, "Archivo creado existosamente en {0}", archivo.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Archivo no localizable en sistema de archivos");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error de entrada/salida");
        }
    }
    
    public static void crearCeldas(JSONArray jsonArray, String[] encabezado,Row fila, Sheet pagina, CellStyle datosEstilo ){
    for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject json2 = (JSONObject) jsonArray.get(i);
            fila = pagina.createRow(i + 1);
            for (int j = 0; j < encabezado.length; j++) {
                String tipo = json2.get(encabezado[j]).getClass().getSimpleName();
                Cell celda = fila.createCell(j);
                 
                if (tipo.equals("Double") || tipo.equals("Short")) {
                    
                  
                    celda.setCellValue((Double) json2.get(encabezado[j]));

                } else if (tipo.equals("Integer")) {
                    
                   celda.setCellValue((Integer) json2.get(encabezado[j]));
                } else if(tipo.equals("String")){
                     celda.setCellValue((String) json2.get(encabezado[j]));
                }else if(tipo.equals("Long")){
                     celda.setCellValue((Long) json2.get(encabezado[j]));
                }
                celda.setCellStyle(datosEstilo);
                pagina.autoSizeColumn(j);
            }
            
        }
    
    }

}
