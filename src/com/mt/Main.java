package com.mt;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numStates = Integer.parseInt(scan.nextLine());
		
		String alphabet = scan.nextLine();
		String stackAlphabet = scan.nextLine();
		
		alphabet = stackAlphabet + " " +alphabet;
		List<String> alphabetArray = Arrays.asList(alphabet.split(" "));
		
		int initialState = Integer.parseInt(scan.nextLine());
		
		String finalStates = scan.nextLine();
		List<String> finalStatesArray = Arrays.asList(finalStates.split(" "));
		
		int numTransitions = Integer.parseInt(scan.nextLine());
		
		ArrayList<String> transitions = new ArrayList<>();

		for (int i = 0; i < numTransitions; i++) {
			transitions.add(scan.nextLine());
		}
		
		Automato aut = new Automato(numStates, alphabetArray, transitions, initialState, finalStatesArray);
		aut.show();
	}

}
