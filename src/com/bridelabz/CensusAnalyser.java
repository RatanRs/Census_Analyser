package com.bridelabz;

	import java.io.IOException;
	import java.io.Reader;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.Iterator;

	import com.opencsv.bean.CsvToBean;
	import com.opencsv.bean.CsvToBeanBuilder;

	public class CensusAnalyser { 
		public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
			try {
				Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
				CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(reader);
				csvToBeanBuilder.withType(IndiaCensusCSV.class);
				csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
				CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
				Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
				int namOfEateries = 0;
				while (censusCSVIterator.hasNext()) {
					namOfEateries++;
					IndiaCensusCSV censusData = censusCSVIterator.next();
				}
				return namOfEateries;
			} catch (IOException e) {
				throw new CensusAnalyserException(e.getMessage(),
						CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
			}
		}

		public static void main(String[] args) throws CensusAnalyserException {

			final String INDIA_CENSUS_CSV_FILE_PATH = "E:\\RFP229\\CensusAnalyser\\IndiaStateCensusData.csv";
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			System.out.println(numOfRecords);
		}
	}

