package com.company;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	private static final int PARAMS_AMOUNT = 2;

    public static void main(String[] args) {
		if (args.length != PARAMS_AMOUNT) {
			System.out.println("Необходимо задать " + PARAMS_AMOUNT + " параметра");
		} else {
			Path taskPath = Paths.get(args[0]);
			Path dictPath = Paths.get(args[1]);
			boolean inputCorrect = true;

			if (taskPath == null) {
				inputCorrect = false;
				printFileIsMissingMsg(args[0]);
			}

			if (dictPath == null) {
				inputCorrect = false;
				printFileIsMissingMsg(args[1]);
			}

			if (inputCorrect) {
				TestApp app = new TestApp(taskPath, dictPath);
				app.start();
			}
		}
    }

	private static void printFileIsMissingMsg(String path) {
		System.out.println("Не удалось обнаружить файл " + path);
	}
}
