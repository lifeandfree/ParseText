package ru.innopolis;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

import static ru.innopolis.ThreadHandler.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ThreadHandlerTest {

	@Test
	void checkOnDuplicateAndBadCh() throws IOException {
		FileReader fileReader = Mockito.mock(FileReader.class);
		BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
		when(bufferedReader.readLine()).
				thenReturn("ааааа авыава ававыа8ы ы аюю.аваы ыафыва уопварпцф.  шкщпшщумь тмешмьк хьмемыт  еьимоешм 48845 ьэымю");
		Indicator indicator = new Indicator(false, false);
		TreeSet<String> treeSetWords = new TreeSet<>();

		ThreadHandler threadHandler = new ThreadHandler("test.txt", (byte) 0, indicator, treeSetWords);
		threadHandler.checkOnDuplicateAndBadCh(bufferedReader);
		assertTrue((treeSetWords.size() == 14));
	}

}