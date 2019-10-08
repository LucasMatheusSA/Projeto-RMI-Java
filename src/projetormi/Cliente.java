/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetormi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author LUCAS MATHEUS
 */
public class Cliente {
    public static void main (String[] args) throws IOException, InterruptedException, RemoteException, NotBoundException{
        Scanner ler = new Scanner(System.in);
        int op = 0;
        
        clean();
        do{
            System.out.println(" ========== M E N U ========== ");
            System.out.println("|                             |");
            System.out.println("|                             |");  
            System.out.println("|    1 - Jogar                |");
            System.out.println("|    0 - Sair                 |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println(" ============================= ");
            System.out.println("");
            System.out.printf("--> Digite uma opção:");
            op = ler.nextInt();
            switch(op){
                case 0: break;
                case 1:
                    menu2();
                    break;
                default: 
                    clean();
                    System.out.printf("!!! '%d' É uma opção invalida !!!\n",op);
                    sleep(2);
                    clean();
                    break;
            
            }
        }while(op != 0);
        
        
        clean();
        System.out.println("Final do programa.");
        
    }
    
    public static void menu2() throws IOException, InterruptedException, RemoteException, NotBoundException{
        Scanner ler = new Scanner(System.in);
        int op = 0;
        
        Registry registro = LocateRegistry.getRegistry(9999);
        JogoVelhaRemote remoto = (JogoVelhaRemote) registro.lookup("JogoVelha");
        
        clean();
        do{
            System.out.println(" ========== M E N U ========== ");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println("|    1 - SinglePlayer         |");
            System.out.println("|    2 - MultPlayer           |");
            System.out.println("|    0 - Voltar               |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println("|                             |");
            System.out.println(" ============================= ");
            System.out.println("");
            System.out.printf("--> Digite uma opção:");
            op = ler.nextInt();
            switch(op){
                case 0: break;
                case 1:
                    SinglePlayer(remoto);
                    break;
                case 2:
                    MultPlayer(remoto);
                    break;
                default: 
                    clean();
                    System.out.printf("!!! '%d' É uma opção invalida !!!\n",op);
                    sleep(2);
                    clean();
                    break;
            
            }
        }while(op != 0);
        clean();
    }
    
    public static void clean() throws IOException {  
        for (int i = 0; i < 100; ++i)  
            System.out.println(); 
    } 
    
    public static void sleep(float num) throws InterruptedException{
        Thread.sleep((long) (num*1000));
    }
    
    public static int checkGame(int result,String jogo) throws InterruptedException, IOException{
        if(result == 2){
            clean();
            System.out.println(jogo);
            System.out.printf("--> Você perdeu  :'( ...");
            sleep(2);
            return 2;
        }else if(result == 1){
            clean();
            System.out.println(jogo);
            System.out.printf("--> VOCÊ VENCEEEU !!!  : D ");
            sleep(2);
            return 1;
        }else if(result ==  3){
            clean();
            System.out.println("--> Empate! ¯\\_(ツ)_/¯ ");
            sleep(2);
            return 3;
        }
        return 0;
    }
    
    public static int checkGameMult(int result, String jogo,int codigo) throws IOException, InterruptedException{
        if(result != codigo && result != 0 && result != 3){
            clean();
            System.out.println(jogo);
            System.out.printf("--> Você perdeu  :'( ...");
            sleep(2);
            return 2;
        }else if(result == codigo){
            clean();
            System.out.println(jogo);
            System.out.printf("--> VOCÊ VENCEEEU !!!  : D ");
            sleep(2);
            return 1;
        }else if(result ==  3){
            clean();
            System.out.println("--> Empate! ¯\\_(ツ)_/¯ ");
            sleep(2);
            return 3;
        }
        return 0;
    }
    
    public static void SinglePlayer(JogoVelhaRemote remoto) throws IOException, InterruptedException{
        Scanner ler = new Scanner(System.in);
        int op = 0;
        
        clean();
        System.out.println(remoto.instrucao());
        remoto.initVet();
        sleep((float) 3.5);
        if(remoto.first() == 1){
            clean();
            System.out.println("--> CPU começa jogando !!!");
            sleep(2);clean();
            System.out.println(remoto.jogaCPU());
        }else{
            clean();
            System.out.println("--> Você começa Jogando !!!");
            sleep(2);clean();
            System.out.println(remoto.printJogo());
        }  
                    
        while(true){
            do{
                System.out.printf("-->Digite o campo: ");
                op = ler.nextInt();
                if(op > 9 || op < 1){
                    clean();
                    System.out.printf("!!! '%d' É uma opção invalida!!!\n",op);
                    sleep(2);
                    clean();
                    System.out.println(remoto.printJogo());
                }else{
                    op = remoto.jogada(op - 1);
                    if(op == 0){
                        clean();
                        System.out.printf("!!! É um campo que já esta ocupado!!!\n",op);
                        sleep(2);
                        clean();
                        System.out.println(remoto.printJogo());
                    }
                }
            }while(op > 9 || op < 1);
                        
            if(checkGame(remoto.endGame(),remoto.printJogo()) != 0){remoto.cleanVet();break;}
            clean();
            System.out.println(remoto.jogaCPU());
            if(checkGame(remoto.endGame(),remoto.printJogo()) != 0){remoto.cleanVet();break;}
        }
        clean();
    }
    
    public static void MultPlayer(JogoVelhaRemote remoto) throws IOException, RemoteException, InterruptedException{
        Scanner ler = new Scanner(System.in);
        int op = 0;
        int statusInicial = remoto.getStatus();
        int codigo = 0;
        String simbolo = new String();
        clean();
        
        if(statusInicial == 0){
           remoto.initVetMult();
           codigo = 1;
           remoto.setStatus(codigo);
           simbolo = "O";
           aguardaOponenteLogar(remoto,codigo);
           System.out.println("--> Oponente Logado, você começa.");
           sleep(2);
           clean();
        }else{
           codigo = 2;
           simbolo = "X";
           System.out.println("--> Oponente Logado, você é o segundo a jogar.");
           remoto.setStatus(2);
           sleep(2);
           clean();
           remoto.setStatus(1);
           aguardaOponenteJogar(remoto,codigo); 
        }
        
        while(true){
            do{ 
                System.out.println(remoto.printJogo());
                System.out.printf("-->Digite o campo: ");
                op = ler.nextInt();
                if(op > 9 || op < 1){
                    clean();
                    System.out.printf("!!! '%d' É uma opção invalida!!!\n",op);
                    sleep(2);
                    clean();
                }else{
                    op = remoto.jogadaMult(op - 1,simbolo);
                    if(op == 0){
                        clean();
                        System.out.printf("!!! É um campo que já esta ocupado!!!\n",op);
                        sleep(2);
                        clean();
                    }
                }
            }while(op > 9 || op < 1);
                        
            if(checkGameMult(remoto.endGame(),remoto.printJogo(),codigo) != 0){remoto.fimJogada(simbolo);break;}
            clean();
            remoto.fimJogada(simbolo);
            aguardaOponenteJogar(remoto,codigo);
            clean();
            op = checkGameMult(remoto.endGame(),remoto.printJogo(),codigo);
            if(op != 0){remoto.fimJogada(simbolo);remoto.cleanVetMult();break;}
            System.out.println("--> Oponente jogou, sua vez.");
            sleep(3);
            clean();
        }
        clean();
    }
    
    public static void aguardaOponenteJogar (JogoVelhaRemote remoto, int status) throws RemoteException, InterruptedException, IOException{
        String pontos = new String();
        
        do{ 
            clean();
            System.out.println("--> Aguardando jogada do oponente " + pontos);
            sleep((float) 0.5);
            if(pontos.length() == 6){
                pontos = "";
            }else{
                pontos += ". ";
            }
        }while(status != remoto.getStatus());
        clean();
    }
    
    public static void aguardaOponenteLogar (JogoVelhaRemote remoto, int status) throws RemoteException, InterruptedException, IOException{
        String pontos = new String();
        
        do{
            clean();
            System.out.println("--> Aguardando oponente logar " + pontos);
            sleep((float) 0.5);
            if(pontos.length() == 6){
                pontos = "";
            }else{
                pontos += ". ";
            }
        }while(status == remoto.getStatus());
        clean();
    }
}
