package com.stackroute.datamunger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;
import com.stackroute.datamunger.reader.CsvQueryProcessor;

public class DataMunger {

	public static void main(String[] args) throws IOException {

		// read the file name from the user
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the File Path: ");
		String file = scan.nextLine();
		
		CsvQueryProcessor cqp = new CsvQueryProcessor(file);
		/*
		 * create object of CsvQueryProcessor. We are trying to read from a file inside
		 * the constructor of this class. Hence, we will have to handle exceptions.
		 */
		Header headers = cqp.getHeader();
		DataTypeDefinitions types = cqp.getColumnType();
		
		/*
		 * call getHeader() method of CsvQueryProcessor class to retrieve the array of
		 * headers
		 */
		
		for(String h: headers.getHeaders()) {
			System.out.println(h);
		}
		
		/*
		 * call getColumnType() method of CsvQueryProcessor class to retrieve the array
		 * of column data types which is actually the object of DataTypeDefinitions
		 * class
		 */
		for(Object t: types.getDataTypes()) {
			System.out.println(t);
		}
		/*
		 * display the columnName from the header object along with its data type from
		 * DataTypeDefinitions object
		 */

	}

}
