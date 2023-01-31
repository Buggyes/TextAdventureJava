import java.util.Random;

public class Combat {

	public void GenerateClue() {
		Random genClue = new Random();
		int clueID = genClue.nextInt(10) + 1;
		switch (clueID) {
		case 1:
			System.out.println("Dica: A cada horda que passa, os inimigos ficam mais fortes, ent�o fique atento! (Pressione Enter para continuar)");
			break;
		case 2:
			System.out.println("Dica: Dependendo do qu�o forte � o inimigo que voc� derrota, mais ouro ele pode deixar ao ser abatido! (Pressione Enter para continuar)");
			break;
		case 3:
			System.out.println("Dica: Feiti�os ignoram a armadura dos inimigos, isto � �timo para causar dano em inimigos com muita armadura! (Pressione Enter para continuar)");
			break;
		case 4:
			System.out.println("Dica: A cada vez que seu personagem volta � masmorra, ele tem uma chance de encontrar uma po��o de cura largada no ch�o! (Pressione Enter para continuar)");
			break;
		case 5:
			System.out.println("Dica: Defender pode ser uma �tima op��o para reduzir o dano que voc� receber� do inimigo, \n reduzindo o dano recebido praticamente pela metade! (Pressione Enter para continuar)");
			break;
		case 6:
			System.out.println("Dica: -->TODAS<-- as a��es que voc� faz gastam um turno, ent�o pense bem antes de agir! (Pressione Enter para continuar)");
			break;
		case 7:
			System.out.println("Dica: O feiti�o de trov�o � o �nico ataque do jogo que � imposs�vel de ser esquivado, \n use isso ao seu favor para derrotar inimigos que possuem uma chance de esquiva alta! (Pressione Enter para continuar)");
			break;
		case 8:
			System.out.println("Curiosidade: A classe ''Mago'' � a �nica classe do jogo que pode a usar feiti�os desde a horda 1. \n Por�m, � a classe com menos dano de ataque e esquiva base do jogo. (Pressione Enter para continuar)");
			break;
		case 9:
			System.out.println("Curiosidade: A classe ''Arqueiro'' � a classe com maior dano de ataque e esquiva base do jogo. \n Por�m, possui a menor vida e armadura base dentre as 3 classes. (Pressione Enter para continuar)");
			break;
		case 10:
			System.out.println("Curiosidade: A classe ''Cavaleiro'' � a classe com maior vida e defesa base do jogo. \n Por�m, possui quase a mesma chance de esquiva que a de um golem, ou seja, quase 0. (Pressione Enter para continuar)");
			break;
		default:
			break;
		}
	}
	public float damage(float targetHp, float agressorAtk, float targetDef, float targetDodge, boolean plIsGuarding) {
		Random dodgeC = new Random();
		float dmgReduct;
		float dodgeAtempt = dodgeC.nextFloat() * 90;
		if (dodgeAtempt <= targetDodge) {
			return targetHp;
		}
		if (plIsGuarding == true) {
			dmgReduct = targetDef / (targetDef + 100);
			dmgReduct *= 2;
			float remainHp = targetHp - (agressorAtk - (agressorAtk * dmgReduct));
			return remainHp;
		}
		else {
			dmgReduct = targetDef / (targetDef + 100);
			float remainHp = targetHp - (agressorAtk - (agressorAtk * dmgReduct));
			return remainHp;
		}
	}

	public float calcDamageTaken(float targetHp, float agressorAtk, float targetDef, boolean plIsGuarding) {
		float dmgReduct;
		if (plIsGuarding == true) {
			dmgReduct = targetDef / (targetDef + 100);
			dmgReduct *= 1.5;
			float dmgTaken = (agressorAtk - (agressorAtk * dmgReduct));
			return dmgTaken;
		}
		else {
			dmgReduct = targetDef / (targetDef + 100);
			float dmgTaken = (agressorAtk - (agressorAtk * dmgReduct));
			return dmgTaken;
		}
	}

	public float calcSpellDmg(byte opt, float fireBallDmg, float lightningDmg, float targetHp, float targetDodge) {
		float remainHp;
		Random dodgeC = new Random();
		switch (opt) {
		case 1:
			float dodgeAtempt = dodgeC.nextFloat() * 90;
			if (dodgeAtempt <= targetDodge) {
				return targetHp;
			}
			remainHp = targetHp - fireBallDmg;
			return remainHp;
		case 2:
			remainHp = targetHp - lightningDmg;
			return remainHp;
		default:
			return 0;
		}
	}

	public float calcHealingSpell(float maxTargetHp, float targetActualHp, float healingStrenght) {
		targetActualHp += healingStrenght;
		if (targetActualHp > maxTargetHp) {
			return maxTargetHp;
		} else {
			return targetActualHp;
		}
	}

	public float drinkPot(float userActualHp, float userMaxHp) {
		float potHeal = (userMaxHp / (float)1.6);
		float hpLost = (userActualHp - userMaxHp);
		if (potHeal <= hpLost) {
			userActualHp += potHeal;
			return userActualHp;
		} else {
			userActualHp = userMaxHp;
			return userActualHp;
		}
	}

	public float calcManaSpent(float actualMana, float manaCost) {
		float manaRemain = actualMana - manaCost;
		return manaRemain;
	}

	public void showSituation(String objName, float objHp, float objMaxHp, float objAtk, float objDef, float objDodge, float objMana, float healManaCost, byte potAmount) {
		if (objMana >= healManaCost) {
			System.out.println("");
			System.out.println("Condi��o de: " + objName);
			System.out.println("Vida: " + objHp + "/" + objMaxHp);
			System.out.println("Ataque: " + objAtk);
			System.out.println("Defesa: " + objDef);
			System.out.println("Esquiva: " + objDodge);
			System.out.println("Mana: " + objMana);
			System.out.println("");
			System.out.println("A��es:");
			System.out.println("1: atacar;");
			System.out.println("2: defender;");
			System.out.println("3: usar feiti�o;");
			System.out.println("4: beber po��o. ("+potAmount+"/3)");
		}
		else if (objMana < 0) {
			System.out.println("");
			System.out.println("Condi��o de: " + objName);
			System.out.println("Vida: " + objHp + "/" + objMaxHp);
			System.out.println("Ataque: " + objAtk);
			System.out.println("Defesa: " + objDef);
			System.out.println("Esquiva: " + objDodge);
		}
		else if (objMana < healManaCost) {
			System.out.println("");
			System.out.println("Condi��o de: " + objName);
			System.out.println("Vida: " + objHp + "/" + objMaxHp);
			System.out.println("Ataque: " + objAtk);
			System.out.println("Defesa: " + objDef);
			System.out.println("Esquiva: " + objDodge);
			System.out.println("");
			System.out.println("A��es:");
			System.out.println("1: atacar;");
			System.out.println("2: defender;");
			System.out.println("3: usar feiti�o;");
			System.out.println("4: beber po��o. ("+potAmount+"/3)");
		}
	}
}
