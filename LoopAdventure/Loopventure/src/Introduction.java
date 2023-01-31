import java.util.Scanner;

public class Introduction {
	public void intro(boolean alive, String plName, float hp, float atk, float def, float dodge, float mana) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("");
		System.out.println("Este jogo de RPG possui os seguintes atributos:");
		System.out.println("Vida: Representa a saúde do seu personagem, que abaixa ao tomar dano. Se ela chegar a 0, seu personagem morre.");
		System.out.println("Ataque: Mostra o dano que seu personagem causa com ataques normais.");
		System.out.println("Defesa: reduz o dano que seu personagem recebe.");
		System.out.println("Esquiva: É a chance de seu personagem esquivar de um ataque (de 0 a 100).");
		System.out.println("Mana: É o que possibilita seu personagem usar feitiços.(Pressione Enter para continuar)");
		scan.nextLine();
		System.out.println("");
		System.out.println("Seu personagem possui os seguintes atributos:");
		System.out.println("Vida: " + hp);
		System.out.println("Ataque: " + atk);
		System.out.println("Defesa: " + def);
		System.out.println("Esquiva: " + dodge);
		System.out.println("Mana: " + mana + " (Pressione Enter para continuar)");
		scan.nextLine();
		System.out.println("");
		System.out.println("Independete da sua escolha de classe, ela sempre vai começar com uma poção de vida no inventário.");
		System.out.println("Além disso, você ganha dinheiro dos inimigos abatidos para comprar equipamentos mais fortes.");
		System.out.println("Dito isso, boa sorte " + plName + ", pois você vai precisar.(Pressione Enter para continuar)");
		scan.nextLine();
	}
}
