import java.io.IOException;
import java.util.Scanner;

public class MainGame {
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Aviso!
	//Caso o código esteja dando o erro "Java.io.FileNotFoundExeption", é provável ser a rota dos arquivos que estão causando.
	
	//Solução:
	//1: Vá para o script "FileManagement"
	//2: Entre nos arquivos do jogo(Loopventure) e veja o local que os arquivos com erro estão(exemplo: C:\\Users\\Sérgio\\OneDrive\\Área de Trabalho).
	//3: Após isso, copie e cole o local dos respectivos arquivos no script.
	//4: no final da rota que foi colada, coloque "\\(nome do arquivo).txt" para o java conseguir acessar, ler e modificar o arquivo.
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) throws IOException {
		boolean playAgain = true;
		MainGame mg = new MainGame();
		do {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		Introduction introd = new Introduction();
		Item item = new Item();
		FileManagement fm = new FileManagement();
		Combat combat = new Combat();
		Enemy en = new Enemy();
		Money money = new Money();
		boolean plIsAlive = true, enIsAlive, plIsGuarding = false, hasASave = false;
		hasASave = fm.CheckSave();
		String plName = "", enName = "";
		float hordeCount = 0;
		byte enID = 0;
		float plMaxHp = 0, plAtk = 0, plDef = 0, plDodge = 0, plMaxMana = 0, plGold = 0;
		float enHp = 0, enMaxHp = 0, enAtk = 0, enDef = 0, enDodge = 0;
		float fireBallManaCost = 25, fireBallDmg = 45, lightningManaCost = 20, lightningDmg = 30, healManaCost = 15, healAmount = 60;
		byte classOpt = 0, waveCount = 0, choice = 0;
		byte[] potStack = new byte[3];
		fm.LoadScore();
		if(hasASave == false)
		{
			for(int i = 0; i < potStack.length; i++)
			{
				if (i == 1)
				potStack[i] = 1;
				else
				potStack[i] = 0;
			}
			do {
				System.out.println("Qual classe deseja jogar?");
				System.out.println("1: Cavaleiro;");
				System.out.println("2: Arqueiro;");
				System.out.println("3: Mago.");
				classOpt = read.nextByte();
				switch(classOpt) {
				case 1:
					System.out.println("Você selecionou o Cavaleiro!");
					plMaxHp = 200;
					plAtk = 30;
					plDef = 25;
					plDodge = 10;
					plMaxMana = 0;
					break;
				case 2:
					System.out.println("Você selecionou o Arqueiro!");
					plMaxHp = 160;
					plAtk = 40;
					plDef = 15;
					plDodge = 25;
					plMaxMana = 0;
					break;
				case 3:
					System.out.println("Você selecionou o Mago!");
					plMaxHp = 170;
					plAtk = 25;
					plDef = 20;
					plDodge = 15;
					plMaxMana = 100;
					break;
				default:
					System.out.println("Selecione apenas 1, 2 e 3!");
					break;
				}
			}while(classOpt != 1 && classOpt != 2 && classOpt != 3);
			System.out.println("Digite o nome do seu personagem (utilize ''_'' no lugar dos espaços):");
			plName = read.next();
			introd.intro(plIsAlive, plName, plMaxHp, plAtk, plDef, plDodge, plMaxMana);
			mg.skipLines(10);
		}
		else
		{
			float potAmount = 0;
			for(int i = 0; i < potStack.length; i++)
			{
				potAmount += potStack[i];
			}
			plName = fm.GetName(plName);
			Float[] savedStats = fm.GetStats();
			plMaxHp = (float)savedStats[0]; plAtk = (float)savedStats[1]; plDef = (float)savedStats[2]; plDodge = (float)savedStats[3]; plMaxMana = (float)savedStats[4]; fireBallDmg = (float)savedStats[5]; lightningDmg = (float)savedStats[6]; healAmount = (float)savedStats[7]; plGold = (float)savedStats[8]; potAmount = (float)savedStats[9]; hordeCount = (float)savedStats[10];
			for(int i = 0; i < potStack.length; i++)
			{
				if(potAmount > 0)
				{
					potStack[i] = 1;
					potAmount--;
				}
			}
		}
		do {
			mg.skipLines(4);
			byte potAmount = 0;
			for(int i = 0; i < potStack.length; i++)
			{
				potAmount += potStack[i];
			}
			fm.DeleteSave();
			fm.SaveGame(plName, plMaxHp, plAtk, plDef, plDodge, plMaxMana, fireBallDmg, lightningDmg, healAmount, plGold, potAmount, hordeCount);
			waveCount = 0;
			hordeCount++;
			long showHorde = (long)hordeCount;
			System.out.println("Horda "+showHorde+".");
			float plHp = plMaxHp, plMana = plMaxMana;
			do { //Começo das hordas
				enIsAlive = true;
				enID = en.spawn(enID);
				enName = en.defineName(enID, enName);
				float[] enStat = en.setStats(enID, hordeCount);
				enHp = enStat[0];
				enMaxHp = enStat[0];
				enAtk = enStat[1];
				enDef = enStat[2];
				enDodge = enStat[3];
				System.out.println((waveCount+1)+"º inimigo:");
				do {//Começo da batalha
					plIsGuarding = false;
					combat.showSituation(enName, enHp, enMaxHp, enAtk, enDef, enDodge, -1, 0, potAmount);
					combat.showSituation(plName, plHp, plMaxHp, plAtk, plDef, plDodge, plMana, healManaCost, potAmount);
					choice = read.nextByte();
					switch(choice)
					{
					case 1://Se o jogador escolhe atacar
						float tempEnHp = enHp;
						tempEnHp = combat.damage(enHp, plAtk, enDef, enDodge, plIsGuarding);
						if(tempEnHp == enHp)
						{
							System.out.println(enName+" esquivou seu ataque!");
						}
						else
						{
							float dmgTaken = combat.calcDamageTaken(tempEnHp, plAtk, enDef, plIsGuarding);
							if(dmgTaken < 0)
							{
								tempEnHp = enHp;
								dmgTaken = 0;
							}
							System.out.println(enName+" recebeu "+dmgTaken+" de dano!");
							enHp = tempEnHp;
						}
						break;
					case 2://Se o jogador escolhe defender
						plIsGuarding = true;
						break;
					case 3://Se o jogador escolhe usar um feitiço
						if(plMaxMana >= healManaCost)//Se o jogador possui mana
						{
							System.out.println("Feitiços disponíveis:");
							System.out.println("1: Bola de fogo | causa "+fireBallDmg+" de dano e custa "+fireBallManaCost+" de mana");
							System.out.println("2: Trovão | causa "+lightningDmg+" de dano e custa "+lightningManaCost+" de mana");
							System.out.println("3: Cura | restaura "+healAmount+" pontos de vida e custa "+healManaCost+" de mana");
							byte alte = read.nextByte();
							switch(alte)
							{
							case 1:
								if(plMana < fireBallManaCost)
								{
									System.out.println(plName+" não tem mana o suficiente para fazer isso!");
									break;
								}
								tempEnHp = combat.calcSpellDmg(alte, fireBallDmg, lightningDmg, enHp, enDodge);
								plMana = combat.calcManaSpent(plMana, lightningManaCost);
								if(tempEnHp == enHp)
								{
									System.out.println(enName+" esquivou seu ataque!");
								}
								else
								{
									System.out.println(enName+" recebeu "+fireBallDmg+" de dano!");
									enHp = tempEnHp;
								}
								break;
							case 2:
								if(plMana < lightningManaCost)
								{
									System.out.println(plName+" não tem mana o suficiente para fazer isso!");
									break;
								}
								tempEnHp = combat.calcSpellDmg(alte, fireBallDmg, lightningDmg, enHp, enDodge);
								plMana = combat.calcManaSpent(plMana, lightningManaCost);
								if(tempEnHp == enHp)
								{
									System.out.println(enName+" esquivou seu ataque!");
								}
								else
								{
									System.out.println(enName+" recebeu "+lightningDmg+" de dano!");
									enHp = tempEnHp;
								}
								break;
							case 3:
								if(plMana < healManaCost)
								{
									System.out.println(plName+" não tem mana o suficiente para fazer isso!");
									break;
								}
								plHp = combat.calcHealingSpell(plMaxHp, plHp, healAmount);
								System.out.println(plName+" recuperou "+healAmount+" de vida!");
								break;
							default:
								System.out.println(plName+" não se lembrou como se usa feitiços!");
								break;
							}
						}
						else
						{
							System.out.println(plName+" não sabe usar feitiços");
						}
						break;
					case 4://Se o jogador escolhe beber uma poção
						for(int i = 0; i < potStack.length; i++)
						{
							if(potStack[i] > 0)
							{
								potAmount--;
								potStack[i] = 0;
								plHp = combat.drinkPot(plHp, plMaxHp);
								System.out.println(plName+" bebe uma poção e recupera "+(plMaxHp/(float)1.6)+" de vida");
								break;
							}
						}
						break;
					default:
						System.out.println(plName+" está pensando demais...");
						break;
					}
					read.nextLine();
					mg.skipLines(1);
					if(enHp <= 0)
					{
						enIsAlive = false;
						System.out.println(enName+" foi abatido(a)! (Pressione Enter para continuar)");
						plGold = money.earnMoney(plGold, enID, hordeCount);
					}
					else
					{
						float tempPlHp = plHp;
						tempPlHp = combat.damage(plHp, enAtk, plDef, plDodge, plIsGuarding);
						if(tempPlHp == plHp)
						{
							System.out.println(plName+" esquivou do ataque de "+enName+"! (Pressione Enter para continuar)");
						}
						else
						{
							float dmgTaken = combat.calcDamageTaken(tempPlHp, enAtk, plDef, plIsGuarding);
							if(dmgTaken < 0)
							{
								dmgTaken = 0;
								tempPlHp = plHp;
							}
							System.out.println(plName+" recebeu "+dmgTaken+" de dano! (Pressione Enter para continuar)");
							plHp = tempPlHp;
						}
					}
					if(plHp <= 0)
					{
						plIsAlive = false;
					}
					read.nextLine();
					mg.skipLines(3);
				}while(plIsAlive && enIsAlive);
				waveCount++;
			}while(plIsAlive == true && waveCount < 3);
			if(plIsAlive == false)
			{
				break;
			}
			float chestPrice = 0;
			int[] chest = new int[3];
			for(int i = 0; i < chest.length; i++)
			{
				chest[i] = item.pickID();
			}
			boolean quitChestSelect = false;
			byte[] chestOpen = new byte [3];
			for(int i = 0; i < chestOpen.length; i++)
			{
				chestOpen[i] = 0;
			}
			chestPrice = money.chestCost(chestPrice, hordeCount);
			do {//Jogador encontra os baús
				mg.skipLines(2);
				System.out.println(plName+" encontra 3 baús, que só podem ser destrancados com "+chestPrice+" moedas cada.");
				mg.skipLines(1);
				System.out.println(plName+" possui "+plGold+" moedas");
				mg.skipLines(2);
				System.out.println("Qual baú "+plName+" irá escolher?");
				System.out.println("1: 1º baú;");
				System.out.println("2: 2º baú;");
				System.out.println("3: 3º baú;");
				System.out.println("4: Escolher nenhum baú.");
				int chestOpt = read.nextInt();
				if(chestOpt < 4 && chestOpt > 0)
				{
					if(chestOpen[(chestOpt-1)] == 1)
					{
							System.out.println("Este baú já foi aberto!");
					}
					else if(plGold < chestPrice && chestOpt < 4 && chestOpt > 0)
					{
						System.out.println(plName+" não tem dinheiro para abrir esse baú!.");
					}
					if(chestOpen[(chestOpt-1)] == 0 && plGold >= chestPrice)
					{
						plGold = money.spendMoney(chestPrice, plGold);
						chestOpen[(chestOpt-1)] = 1;
						String itemName = ""; 
						itemName = item.setName(chest[(chestOpt-1)], itemName);
						float[] stats = item.setBuff(chest[(chestOpt-1)], plMaxHp, plAtk, plDef, plDodge, plMaxMana, fireBallDmg, lightningDmg, healAmount);
						plMaxHp = stats[0]; plAtk = stats[1]; plDef = stats[2]; plDodge = stats[3]; plMaxMana = stats[4]; fireBallDmg = stats[5]; lightningDmg = stats[6]; healAmount = stats[7];
						item.showItem(chest[(chestOpt-1)], itemName, plName);
					}
				}
				if(chestOpt >= 4 || chestOpt <= 0)
				{
					System.out.println(plName+" resolveu voltar á masmorra. (Pressione Enter para continuar)");
					quitChestSelect = true;
				}
				read.nextLine();
				mg.skipLines(3);
			}while(quitChestSelect == false);
			read.nextLine();
			for(int i = 0; i < potStack.length; i++)
			{
				if(potStack[i] == 0)
				{
					potStack[i] = item.acquirePot(potStack[i], plName);
					if (potStack[i] == 1)
					{
						break;
					}
				}
			}
			mg.skipLines(2);
			potAmount = 0;
			for(int i = 0; i < potStack.length; i++)
			{
				potAmount += potStack[i];
			}
			combat.GenerateClue();
			read.nextLine();
			fm.DeleteSave();
			fm.SaveGame(plName, plMaxHp, plAtk, plDef, plDodge, plMaxMana, fireBallDmg, lightningDmg, healAmount, plGold, potAmount, hordeCount);
		}while(plIsAlive);
		System.out.println(plName+" morreu em combate.");
		fm.SaveScore(plName, hordeCount);
		fm.DeleteSave();
		hasASave = false;
		mg.skipLines(2);
		do {//Se o jogador morre
			long showHorde = (long)hordeCount;
			System.out.println(plName+" chegou até a horda "+showHorde);
			System.out.println("Deseja jogar novamente?(sim/não)");
			String playAgainOpt = "";
			playAgainOpt = read.next();
			if(playAgainOpt.contentEquals("Sim") || playAgainOpt.contentEquals("sim"))
			{
				playAgain = true;
				break;
			}
			else if(playAgainOpt.contentEquals("Não") || playAgainOpt.contentEquals("não"))
			{
				playAgain = false;
				break;
			}
			else
			{
				System.out.println("Digite ''sim'' ou ''não''.");
			}
		}while(1 < 2);
		mg.skipLines(3);
		}while(playAgain == true);
		System.out.println("Jogo feito por:");
		System.out.println("Matheus Eduardo dos Santos e Enzo Duarte Crozeta.");
		mg.skipLines(2);
		System.out.println("Obrigado por jogar nosso jogo!.");
	}
	public void skipLines(int skipQ)
	{
		for(int i = skipQ; i > 0; i--)
		{
			System.out.println("");
		}
	}
}