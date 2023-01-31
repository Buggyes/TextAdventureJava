import java.util.Random;

public class Enemy {
	public byte spawn(byte enID) {
		Random rand = new Random();
		float enSpawn = rand.nextFloat() * 100;
		if (enSpawn <= 12.5) {
			enID = 1;
			return enID;
		}
		else if (enSpawn > 12.5 && enSpawn <= 25) {
			enID = 2;
			return enID;
		}
		else if (enSpawn > 25 && enSpawn <= 37.5) {
			enID = 3;
			return enID;
		}
		else if (enSpawn > 37.5 && enSpawn <= 50) {
			enID = 4;
			return enID;
		}
		else if (enSpawn > 50 && enSpawn <= 62.5) {
			enID = 5;
			return enID;
		}
		else if (enSpawn > 62.5 && enSpawn <= 75) {
			enID = 6;
			return enID;
		}
		else if (enSpawn > 75 && enSpawn <= 87.5) {
			enID = 7;
			return enID;
		}
		else if (enSpawn > 87.5 && enSpawn <= 100) {
			enID = 8;
			return enID;
		}
		else {
			return enID;
		}
	}

	public String defineName(int id, String enName) {
		switch (id) {
		case 1:
			enName = "Goblin";
			System.out.println("Você é emboscado por um " + enName + "!");
			return enName;
		case 2:
			enName = "Árvore ambulante";
			System.out.println("Você é emboscado por uma " + enName + "!");
			return enName;
		case 3:
			enName = "Aranha gigante";
			System.out.println("Você é emboscado por uma " + enName + "!");
			return enName;
		case 4:
			enName = "Golem";
			System.out.println("Você é emboscado por um " + enName + "!");
			return enName;
		case 5:
			enName = "La creatura";
			System.out.println("Você é emboscado por " + enName + "!");
			return enName;
		case 6:
			enName = "Orc";
			System.out.println("Você é emboscado por um " + enName + "!");
			return enName;
		case 7:
			enName = "Fantasma";
			System.out.println("Você é emboscado por um " + enName + "!");
			return enName;
		case 8:
			enName = "Dragão";
			System.out.println("Você é emboscado por um " + enName + "!");
			return enName;
		default:
			enName = "?";
			return enName;
		}
	}

	public float[] setStats(byte id, float hordeCount) {
		float[] enStats = new float[4];
		switch (id) {
		case 1://goblin
			enStats[0] = 50 + (12 * hordeCount);
			enStats[1] = 15 + (12 * hordeCount);
			enStats[2] = 0;
			enStats[3] = 5 + (hordeCount);
			if (enStats[3] >= 60)
				enStats[3] = 60;
			return enStats;
		case 2://Arvore ambulante
			enStats[0] = 70 + (15 * hordeCount);
			enStats[1] = 15 + (5 * hordeCount);
			enStats[2] = 10 + (hordeCount);
			enStats[3] = 0;
			return enStats;
		case 3://Aranha gigante
			enStats[0] = 45 + (10 * hordeCount);
			enStats[1] = 25 + (10 * hordeCount);
			enStats[2] = 0;
			enStats[3] = 30 + ((float)1.5 * hordeCount);
			if (enStats[3] >= 60)
				enStats[3] = 60;
			return enStats;
		case 4://Golem
			enStats[0] = 100 + (20 * hordeCount);
			enStats[1] = 20 + (8 * hordeCount);
			enStats[2] = 22 + (8 * hordeCount);
			enStats[3] = 0;
			return enStats;
		case 5://La creatura
			enStats[0] = 120 + (10 * hordeCount);
			enStats[1] = 40 + (15 * hordeCount);
			enStats[2] = 15 + (5 * hordeCount);
			enStats[3] = 30 + (hordeCount);
			if (enStats[3] >= 60)
				enStats[3] = 60;
			return enStats;
		case 6://Orc
			enStats[0] = 100 + (15 * hordeCount);
			enStats[1] = 30 + (15 * hordeCount);
			enStats[2] = 10 + (5 * hordeCount);
			enStats[3] = 5 + (hordeCount);
			if (enStats[3] >= 60)
				enStats[3] = 60;
			return enStats;
		case 7://Fantasma
			enStats[0] = 50 + (10 * hordeCount);
			enStats[1] = 30 + (20 * hordeCount);
			enStats[2] = 0;
			enStats[3] = 80;
			return enStats;
		case 8://Dragão
			enStats[0] = 160 + (20 * hordeCount);
			enStats[1] = 40 + (20 * hordeCount);
			enStats[2] = 20 + (5 * hordeCount);
			enStats[3] = 10 + (hordeCount);
			if (enStats[3] >= 60)
				enStats[3] = 60;
			return enStats;
		default:
			return enStats;
		}
	}
}
