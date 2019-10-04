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
        
        do{
            System.out.println(" ========== M E N U ========== ");
            System.out.println("|                             |");
            System.out.println("|                             |");  
            System.out.println("|    1 - Jogar                |");
            System.out.println("|    0 - Sair                 |");
            System.out.println("|                             |");
            System.out.println(" ============================= ");
            System.out.println("");
            System.out.println("--> Digite uma opção:");
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
            System.out.println("|    1 - SinglePlayer         |");
            System.out.println("|    2 - MultPlayer           |");
            System.out.println("|    0 - Voltar               |");
            System.out.println("|                             |");
            System.out.println(" ============================= ");
            System.out.println("");
            System.out.printf("--> Digite uma opção:");
            op = ler.nextInt();
            switch(op){
                case 0: break;
                case 1:
                    clean();
                    System.out.println(remoto.instrucao());
                    remoto.initVet();
                    sleep((float) 3.5);
                    if(remoto.first() == 1){
                        System.out.println("--> CPU começa jogando !!!");
                        sleep(2);clean();
                        System.out.println(remoto.jogaCPU());
                    }else{
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
                                System.out.printf("!!! '%d' É um campo que já esta ocupado!!!\n",op);
                                sleep(2);
                                clean();
                                System.out.println(remoto.printJogo());
                            }
                            }
                        }while(op > 9 || op < 1);
                        
                        if(checkGame(remoto.endGame()) != 0){remoto.cleanVet();break;}
                        clean();
                        System.out.println(remoto.jogaCPU());
                        if(checkGame(remoto.endGame()) != 0){remoto.cleanVet();break;}
                    }
                    clean();
                    break;
                case 2:
                    clean();
                    System.out.println(remoto.Teste2());
                    sleep(2);
                    clean();
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
    
    public static int checkGame(int result) throws InterruptedException, IOException{
        if(result == 2){
            clean();
            System.out.println("--> Você perdeu  :'( ...");
            sleep(2);
            return 2;
        }else if(result == 1){
            clean();
            System.out.println("--> VOCÊ VENCEEEU !!!  : D ");
            sleep(2);
            return 1;
        }
        return 0;
    }
}
