import java.util.Random;

public class Item {
	public byte acquirePot(byte pot, String plName) {
		Random rand = new Random();
		int findPot = rand.nextInt(100)+1;
		if (findPot > 50) {
			pot = 1;
			System.out.println(plName + " encontrou uma poção de vida jogada no chão.");
			return pot;
		}
		else {
			return pot;
		}
	}

	public int pickID() {
		Random rand = new Random();
		int id = 0;
		int itemSpawn = rand.nextInt(100);
		if (itemSpawn <= 10) {
			id = 1;
			return id;
		}
		else if (itemSpawn > 10 && itemSpawn <= 20) {
			id = 2;
			return id;
		}
		else if (itemSpawn > 20 && itemSpawn <= 30) {
			id = 3;
			return id;
		}
		else if (itemSpawn > 30 && itemSpawn <= 40) {
			id = 4;
			return id;
		}
		else if (itemSpawn > 40 && itemSpawn <= 50) {
			id = 5;
			return id;
		}
		else if (itemSpawn > 50 && itemSpawn <= 60) {
			id = 6;
			return id;
		}
		else if (itemSpawn > 60 && itemSpawn <= 70) {
			id = 7;
			return id;
		}
		else if (itemSpawn > 80 && itemSpawn <= 90) {
			id = 8;
			return id;
		}
		else if (itemSpawn > 90 && itemSpawn <= 100) {
			id = 9;
			return id;
		}
		else if (itemSpawn > 95 && itemSpawn <= 100) {
			id = 10;
			return id;
		}
		else {
			return id;
		}
	}

	public String setName(int id, String itemName) {
		switch (id) {
		case 1:
			itemName = "as Luvas do Colosso";
			return itemName;
		case 2:
			itemName = "a Espada do Cavaleiro Leal";
			return itemName;
		case 3:
			itemName = "o Escudo quase Perfeito";
			return itemName;
		case 4:
			itemName = "as Botas Vivas";
			return itemName;
		case 5:
			itemName = "o Chapéu do Mago Rebelde";
			return itemName;
		case 6:
			itemName = "o Livro Ancião";
			return itemName;
		case 7:
			itemName = "o Cristal Misterioso";
			return itemName;
		case 8:
			itemName = "o Cajado do Mago Rebelde";
			return itemName;
		case 9:
			itemName = "o Machado do Último Berserker";
			return itemName;
		case 10:
			itemName = "o Saco com Café Ultra Potente";
			return itemName;
		default:
			return itemName;
		}
	}

	public float[] setBuff(int id, float hp, float atk, float def, float dodge, float mana, float fbDmg, float lDmg,
			float hAmount) {
		float[] stats = { hp, atk, def, dodge, mana, fbDmg, lDmg, hAmount };
		switch (id) {
		case 1:
			stats[0] += (hp * 0.15);
			return stats;
		case 2:
			stats[1] += (atk * 0.2);
			return stats;
		case 3:
			stats[2] += (def * 0.1);
			return stats;
		case 4:
			stats[3] += (dodge * 0.1);
			if (stats[3] >= 60)
				stats[3] = 60;
			return stats;
		case 5:
			stats[4] += 40;
			return stats;
		case 6:
			for (int i = 5; i < stats.length; i++) {
				stats[i] += (stats[i] * 0.2);
			}
			return stats;
		case 7:
			for (int i = 0; i < stats.length; i++) {
				stats[i] += (stats[i] * 0.3);
			}
			if (stats[3] >= 60)
				stats[3] = 60;
			return stats;
		case 8:
			for (int i = 4; i < stats.length; i++) {
				stats[i] += (stats[i] * 0.4);
			}
			return stats;
		case 9:
			for (int i = 1; i < 3; i++) {
				stats[i] += (stats[i] * 0.4);
			}
			return stats;
		case 10:
			stats[0] += (stats[0] * 0.3);
			stats[3] += (stats[3] * 0.3);
			if (stats[3] >= 60)
				stats[3] = 60;
			return stats;
		default:
			return stats;
		}
	}

	public void showItem(int itemId, String itemName, String plName) {
		switch (itemId) {
		case 1:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("A vida de " + plName + " foi aumentada em 15%.");
			break;
		case 2:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("O ataque de " + plName + " foi aumentado em 20%.");
			break;
		case 3:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("A defesa de " + plName + " foi aumentada em 10%.");
			break;
		case 4:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("A esquiva de " + plName + " foi aumentada em 10%.");
			break;
		case 5:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("A mana de " + plName + " foi aumentada em 40.");
			break;
		case 6:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("Os feitiços de " + plName + " causam 20% a mais de dano.");
			break;
		case 7:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("Todos os atributos de " + plName + " foram aumentados em 20%.");
			break;
		case 8:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("A mana e os feitiços de " + plName + " foram aumentados em 20%.");
			break;
		case 9:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("A defesa e o ataque de " + plName + " foram aumentados em 20%.");
			break;
		case 10:
			System.out.println(plName + " encontrou " + itemName + "!");
			System.out.println("A vida e esquiva de " + plName + " foram aumentadas em 20%.");
			break;
		default:
			break;
		}
	}
}
