import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class FileManagement {
	// gameFilesPath = Armazena a rota de todos os arquivos do jogo
	// Save.txt = é onde os dados do personagem do jogador ficam salvos
	private String savePath = "Save.txt";
	// Leaderboard.txt = é onde fica armazenado os top 10 personagens que foram mais longe
	private String leaderboardPath = "Leaderboard.txt";

	public void SaveGame(String name, float hp, float atk, float def, float dodge, float mana, float fbDmg,
			float lgtDmg, float healAmount, float gold, byte potAmount, float hordeCount) throws IOException {
		long[] data = { (long) hp, (long) atk, (long) def, (long) dodge, (long) mana, (long) fbDmg, (long) lgtDmg,
				(long) healAmount, (long) gold, (long) potAmount, (long) hordeCount };
		String saveC = "";
		for (int i = 0; i < data.length; i++) {
			if (i == 0) {
				saveC += name + "\n";
			}
			saveC += data[i] + "\n";
		}
		FileWriter fw = new FileWriter(savePath);
		fw.write(saveC);
		fw.close();
	}
	
	public ArrayList<String> ReadLeaderboard() throws FileNotFoundException
	{
		File leaderboardFile = new File(leaderboardPath);//Cria uma variável para encontrar o arquivo
		Scanner scan = new Scanner(leaderboardFile);//Cria um Scanner para ler o arquivo
		String leaderboardC = "";//Cria uma variavel para receber diretamente o que foi lido
		ArrayList<String> leaderboard = new ArrayList<String>();//Cria um arraylist para armazenar o que for lido
		while (scan.hasNextLine()) { //Checa se o arquivo possui mais linhas para serem lidas
			leaderboardC = scan.nextLine(); //Le o arquivo e pula pra próxima linha
			leaderboard.add(leaderboardC); //Adiciona o que foi lido pro arraylist
		}
		scan.close(); //Fecha o Scanner para salvar o que foi lido
		return leaderboard; //Retorna o ArrayList que contém o que foi lido
	}
	
	public void LoadScore() throws FileNotFoundException {
		byte podium = 0;
		ArrayList<String> leaderboard = new ArrayList<String>();//Cria um arraylist para armazenar o pódio
		leaderboard = ReadLeaderboard();
		if (leaderboard.size() >= 10) {
			podium = 10;
		}
		else {
			podium = (byte) leaderboard.size();
		}
		String[][] lbVector = new String[2][podium]; //cria uma variavel para armazenar a pontuação e a pontuação com o nome
		Pattern p = Pattern.compile("\\d+"); //Faz um pattern a ser seguido na hora de ler a pontuação
		for (int i = 0; i < podium; i++) {
			Matcher m = p.matcher(leaderboard.get(i));
			while (m.find()) {
				lbVector[0][i] = m.group();
			}
			lbVector[1][i] = leaderboard.get(i);
		}
		for (int i = 0; i < podium; i++) {
			for (int j = i + 1; j < podium; j++) {// troca os valores se não está em ordem decrescente
				if (Integer.parseInt(lbVector[0][i]) < Integer.parseInt(lbVector[0][j])) {
					String tempScore = lbVector[0][i];
					lbVector[0][i] = lbVector[0][j];
					lbVector[0][j] = tempScore;
					String tempName = lbVector[1][i];
					lbVector[1][i] = lbVector[1][j];
					lbVector[1][j] = tempName;
				}
			}
		}
		if (leaderboard.size() > 0) {
			System.out.println("Melhores Aventureiros:");
			System.out.println("------------------------------");
			System.out.println("|Posição|Pontuação|Personagem|");
			System.out.println("------------------------------");
			for (int i = 0; i < podium; i++) {
				System.out.println("|  " + (i + 1) + "º-      " + lbVector[1][i]);
			}
			System.out.println("----------------------------");
		}
	}

	public void SaveScore(String name, float hordeCount) throws IOException {
		String scoreC = "";
		scoreC += (long) hordeCount + "      " + name+"\n";
		ArrayList<String> leaderboard = new ArrayList<String>(); //Cria um arraylist para armazenar o que for lido
		leaderboard = ReadLeaderboard();
		byte lines = (byte) leaderboard.size();
		for(int i = 0; i < lines; i++)
		{
			scoreC += leaderboard.get(i)+"\n";
		}
		FileWriter fw = new FileWriter(leaderboardPath);
		fw.write(scoreC);
		fw.close();
	}

	public boolean CheckSave() throws FileNotFoundException {
		File save = new File(savePath);
		Scanner scan = new Scanner(save);
		String saveC = "";
		while (scan.hasNextLine()) {
			saveC += scan.nextLine();
		}
		if (saveC.contentEquals("")) {
			scan.close();
			return false;
		}
		else {
			scan.close();
			return true;
		}
	}

	public String GetName(String name) throws FileNotFoundException {
		File save = new File(savePath);
		Scanner scan = new Scanner(save);
		String saveC = "";
		saveC = scan.nextLine();
		scan.close();
		return saveC;
	}

	public Float[] GetStats() throws FileNotFoundException {
		Float[] data = new Float[11]; //variavel pra armazenar os status salvos do jogador
		for (int i = 0; i < data.length; i++) { //inicializa o vetor
			data[i] = (float) 0;
		}
		File save = new File(savePath);
		Scanner scan = new Scanner(save);//Scanner para ler o arquivo
		String bruteData = "";
		int i = 0;
		while (scan.hasNextLine()) {//checa se o arquivo tem mais uma linha para ser lida
			if (scan.hasNextFloat()) {//checa se o que ta escrito é um número
				bruteData = scan.nextLine();
				data[i] = Float.parseFloat(bruteData);//converte o que foi lido em Float
				i++;
			}
			else {//Se o que ta escrito não é um número
				scan.nextLine();
			}
		}
		scan.close();
		return data;
	}

	public void DeleteSave() throws IOException {
		Files.delete(Paths.get(savePath));
		FileWriter fw = new FileWriter(savePath);
		fw.write("");
		fw.close();
	}
}
