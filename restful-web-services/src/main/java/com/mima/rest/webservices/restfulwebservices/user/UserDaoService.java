package com.mima.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static final char PARAMETER_A = 'A';
	private static final char PARAMETER_T = 'T';
	private static final char PARAMETER_C = 'C';
	private static final char PARAMETER_G = 'G';
	private static final String SEQUENCE_A = "AAAA";
	private static final String SEQUENCE_T = "TTTT";
	private static final String SEQUENCE_C = "CCCC";
	private static final String SEQUENCE_G = "GGGG";
	private int countFor = 0;
	private int validColumns = 0;
	
	
	/**
	 * Metodo solicitado en la prueba que valida si existe más de una secuencia de cuatro letras iguales
	 * @param dna
	 * @return
	 */
	public boolean isMutant(String[] dna) {
		
		int sizeArr = dna.length;
		char[] arrDna = new char[sizeArr*sizeArr];
		int countRow = 0;
		int countColumn = 0;
		int countObl = 0;
		int result = 0;
				
		//Validaciones del arreglo 
		if(!this.validationStr(dna)) {
			return false;
		}
			
		arrDna = this.createArray(dna);
		
		//Valida los datos de la petición
		if(!this.validations(arrDna)) {
			return false;
		}
			
		for(int i = 0; i < arrDna.length; i++) {
			System.out.print(arrDna[i]);
		}
		
		//Busca secuencias horizontales
		countRow = validateRows(dna);
		
		if(countRow > 1) {
			return true;
		}
			
		//Busca secuencias verticales
		countColumn = validateColumns(arrDna, sizeArr);
		result = countRow + countColumn;
		if(result > 1) {
			return true;
		}
		
		//Busca secuencias diagonales
		countObl = this.validateDiagonals(arrDna, sizeArr);
		result = result + countObl;
		
		if(result > 1) {
			return true;
		}
			
		return false;
	}
	
	/**
	 * Crea un arreglo de caracteres con la información enviada en la petición
	 * @param dna
	 * @return
	 */
	public char[] createArray(String[] dna) {
		int sizeArr = dna.length;
		char[] arrDna = new char[sizeArr*sizeArr];
		int aux = 0;
		
		for(int i = 0; i < dna.length; i++ ) {
			//Cada posición del arreglo, se convierte en un arreglo de caracteres 
			char[] ch = dna[i].toCharArray();
			
			for(int j = 0; j < dna.length; j++) {
				arrDna[j + aux] = ch[j];
			}
			
			//Cada posición del arreglo tiene el mismo tamaño del arreglo
			aux = aux + dna.length;
		}
		
		return arrDna;
	}
	
	//Valida que las letras contenidas en el String sean las correctas
	public boolean validations(char[] dna) {
		for(int i = 0; i < dna.length; i++) {
			if((dna[i] != PARAMETER_A) && (dna[i] != PARAMETER_C) && (dna[i] != PARAMETER_G) && (dna[i] != PARAMETER_T)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Validaciones del arreglo
	 * @param dna
	 * @return
	 */
	public boolean validationStr(String[] dna) {
		int sizeValidation = dna.length;
		
		//Valida si es cuadrada, requisito de la prueba 
		for(int i = 0; i < dna.length; i++) {
			//Cada posición del arreglo es una fila que debe tener el mismo tamaño del arreglo para que sea cuadrada
			if(dna[i].length() != sizeValidation) {
				return false;
			}
		}
		
		//Valida tamaño del arreglo, si es menor a 4 no va a encontrar una secuencia de 4 letras iguales
		if(sizeValidation < 4) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Valida la existencia de secuencias horizontales utilizando contains
	 * @param ch
	 * @return
	 */
	public int validateRows(String[] dna) {
		int count = 0;
		
		for(int i = 0; i < dna.length; i++) {
			if(dna[i].contains(SEQUENCE_A)) {
				count = count + 1;
			}
            if(dna[i].contains(SEQUENCE_C)) {
            	count = count + 1;
			}
            if(dna[i].contains(SEQUENCE_G)) {
            	count = count + 1;
			}
            if(dna[i].contains(SEQUENCE_T)) {
            	count = count + 1;
			}	
		}
		
		return count;
	}
	
	/**
	 * Valida si las columnas tienen secuencias
	 * @param dna
	 * @param size
	 * @param init
	 * @return
	 */
	public int validateColumns(char[] dna, int size) {
		char[] arr = new char[size];
		int index = 0;
		int count = 0;
		int result = 0;
		String[] columns = new String[size]; 
		
		//llena el arreglo con los valores correspondientes a una columna
		for(int j = 0; j < size; j++) {
			arr[j] = dna[index];
			index = index + size;
			
			//Valida que llegue a la posicion del arreglo correspondiente y resetea el indice para que tome las nuevas posiciones
			//Llega hasta la ultima posición de cada posición en el arreglo
			//Si llega a la ultima posición, pasa al siguiente posición del arreglo y guarda el valor de la posición del index 
			if((index == dna.length - size + count) && (count < size)) {
				arr[j + 1] = dna[index];
				index = count + 1;
				columns[count] = String.valueOf(arr);
				count = count + 1;
				arr = new char[size];
				j = -1;
				
			}
			if(index == dna.length) {
				break;
			}
		}
		
		//Se tiene un arreglo que en cada tamaño del String corresponde a una columna que se convirtio en una fila, se valida si tiene caracteres consecutivos
		//Con el arreglo de Strings que corresponden a las columnas, utilizamos el metodo de validacion
		result = validateRows(columns);
		
		return result;
	}

    public int validateDiagonals(char[] ch, int size) {
    	int positionInit = 0;
    	int count = 0;
    	int goal = 3;
    	int result = 0;
    	int index = 0;
    	//Variable que me a identificar el lugar en el arreglo de caracteres
    	int position = size + 1;
    	
    	
    	for(int i = 0; i < ch.length; i++) {
    		if(position < ch.length && ch[i] == ch[position]) {
    			//La variable contador sirve para validar la cantidad de aciertos
    			count = count + 1;
    			index = index + 1;
    			i = position - 1;
    			position = position + (size + 1);
    			
    			if(count == goal) {
    				result = result + 1;
    			}
    	    }
    		else {
    			count = 0;
    			//Con esta variable corremos la posición 
    			positionInit = positionInit + 1;
    			i = positionInit - 1;
    			position = i + size + 2;
    		}
    	}
	    return result;
	}
	
	public int getCountFor() {
		return countFor;
	}

	public void setCountFor(int countFor) {
		this.countFor = countFor;
	}

	public int getValidColumns() {
		return validColumns;
	}

	public void setValidColumns(int validColumns) {
		this.validColumns = validColumns;
	}

}
