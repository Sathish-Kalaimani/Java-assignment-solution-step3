package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	private static ArrayList<String> lines=  new ArrayList<String>();
	private static Header header = new Header();
		// parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		
		String line=null;
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		}catch(FileNotFoundException e) {
			System.out.println("Check the File Name");
			throw e;
		}
		try {
			for (int i=0;i<=1;i++) {
				line = reader.readLine();
				lines.add(line);
			}
			reader.close();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		header.setHeaders(lines.get(0).toString().split(","));
			
	}

	/*
	 * implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	@Override
	public Header getHeader() throws IOException {
		
		header.getHeaders();
		
		return header;
		
		
	}
	
	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {
		
		
	}

	/*
	 * implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
			
		DataTypeDefinitions d = new DataTypeDefinitions();
		d.setDataTypes(findColumntype());
		d.getDataTypes();
		
		return d;
	}
	
		private String[] findColumntype() {
			
			String[] dtypes = LevelLength();
			Object[] o = new Object[dtypes.length];
			for(int i=0;i<dtypes.length;i++) {
			try {
				Object I = Integer.parseInt(dtypes[i]);
				o[i] = I.getClass().getName();
				
			}catch(NumberFormatException e) {
				try {
				Object I = Double.parseDouble(dtypes[i]);
				o[i]=I.getClass().getName();
			}catch(NumberFormatException ex) {
				o[i]= dtypes[i].getClass().getName();
			}
			}
			}
			for(int i=0;i<o.length;i++) {
				dtypes[i]= (String) o[i];
			}
			return dtypes;
		}
		
		public String[] LevelLength() {
			
			String[] types = lines.get(1).toString().split(",");
			String[] Ntype = new String[lines.get(0).toString().split(",").length];
			for(int i=0;i<Ntype.length;i++) {
				try {
				Ntype[i] = types[i];
			}catch(ArrayIndexOutOfBoundsException e) {
				Ntype[i] = "String";
			}
			}
			return Ntype;
		}
}
