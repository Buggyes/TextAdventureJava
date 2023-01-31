import java.util.Scanner;

public class Introduction {
	public void intro(boolean alive, String plName, float hp, float atk, float def, float dodge, float mana) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("");
		System.out.println("Este jogo de RPG possui os seguintes atributos:");
		System.out.println("Vida: Representa a sa�de do seu personagem, que abaixa ao tomar dano. Se ela chegar a 0, seu personagem morre.");
		System.out.println("Ataque: Mostra o dano que seu personagem causa com ataques normais.");
		System.out.println("Defesa: reduz o dano que seu personagem recebe.");
		System.out.println("Esquiva: � a chance de seu personagem esquivar de um ataque (de 0 a 100).");
		System.out.println("Mana: � o que possibilita seu personagem usar feiti�os.(Pressione Enter para continuar)");
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
		System.out.println("Independete da sua escolha de classe, ela sempre vai come�ar com uma po��o de vida no invent�rio.");
		System.out.println("Al�m disso, voc� ganha dinheiro dos inimigos abatidos para comprar equipamentos mais fortes.");
		System.out.println("Dito isso, boa sorte " + plName + ", pois voc� vai precisar.(Pressione Enter para continuar)");
		scan.nextLine();
	}
}
