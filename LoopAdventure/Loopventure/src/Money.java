
public class Money {
	public float earnMoney(float plMon, int enID, float hordeCount) {
		float goldGained = 0;
		switch (enID) {
		case 1:
			goldGained = 20 * hordeCount;
			plMon += goldGained;
			System.out.println("+" + goldGained + " de ouro");
			return plMon;
		case 2:
			goldGained = 40 * hordeCount;
			plMon += goldGained;
			System.out.println("+" + goldGained + " de ouro");
			return plMon;
		case 3:
			goldGained = 60 * hordeCount;
			plMon += goldGained;
			System.out.println("+" + goldGained + " de ouro");
			return plMon;
		case 4:
			goldGained = 80 * hordeCount;
			plMon += goldGained;
			System.out.println("+" + goldGained + " de ouro");
			return plMon;
		case 5:
			goldGained = 100 * hordeCount;
			plMon += goldGained;
			System.out.println("+" + goldGained + " de ouro");
			return plMon;
		case 6:
			goldGained = 60 * hordeCount;
			plMon += goldGained;
			System.out.println("+" + goldGained + " de ouro");
			return plMon;
		case 7:
			goldGained = 80 * hordeCount;
			plMon += goldGained;
			System.out.println("+" + goldGained + " de ouro");
			return plMon;
		case 8:
			goldGained = 100 * hordeCount;
			plMon += goldGained;
			System.out.println("+" + goldGained + " de ouro");
			return plMon;
		default:
			return 0;
		}
	}

	public float chestCost(float cost, float hordeCount) {
		cost = 50 * hordeCount;
		return cost;
	}

	public float spendMoney(float price, float money) {
		money -= price;
		return money;
	}
}
