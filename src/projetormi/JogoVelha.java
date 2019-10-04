/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetormi;

import static java.lang.Math.random;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 *
 * @author LUCAS MATHEUS
 */
public class JogoVelha extends UnicastRemoteObject implements JogoVelhaRemote
{   
    
    String vet[] = new String[9];
    
    public JogoVelha () throws RemoteException{
        super();
    }

    @Override
    public String Teste2() throws RemoteException{
        StringBuilder sb = new StringBuilder();
        sb.append("Rolou negão 2!!!");
        System.out.println("rolou engo 2 !!!");
        return sb.toString();
    }

    @Override
    public String printJogo() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        sb.append("  _____ _____ _____ \n");
        sb.append(" |     |     |     | \n");
        sb.append(" |  "+ vet[6] +"  |  "+ vet[7] +"  |  "+ vet[8] +"  | \n");
        sb.append(" |_____|_____|_____| \n");
        sb.append(" |     |     |     | \n");
        sb.append(" |  "+ vet[3] +"  |  "+ vet[4] +"  |  "+ vet[5] +"  | \n");
        sb.append(" |_____|_____|_____| \n");
        sb.append(" |     |     |     | \n");
        sb.append(" |  "+ vet[0] +"  |  "+ vet[1] +"  |  "+ vet[2] +"  | \n");
        sb.append(" |_____|_____|_____| \n");
        return sb.toString();
    }

    @Override
    public void initVet() throws RemoteException {
        vet[0] = " ";
        vet[1] = " ";
        vet[2] = " ";
        vet[3] = " ";
        vet[4] = " ";
        vet[5] = " ";
        vet[6] = " ";
        vet[7] = " ";
        vet[8] = " ";
    }

    @Override
    public void cleanVet() throws RemoteException {
        vet[0] = " ";
        vet[1] = " ";
        vet[2] = " ";
        vet[3] = " ";
        vet[4] = " ";
        vet[5] = " ";
        vet[6] = " ";
        vet[7] = " ";
        vet[8] = " ";
    }

    @Override
    public String instrucao() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        sb.append("  _____ _____ _____ \n");
        sb.append(" |     |     |     | \n");
        sb.append(" |  7  |  8  |  9  | \n");
        sb.append(" |_____|_____|_____| \n");
        sb.append(" |     |     |     | \n");
        sb.append(" |  4  |  5  |  6  | \n");
        sb.append(" |_____|_____|_____| \n");
        sb.append(" |     |     |     | \n");
        sb.append(" |  1  |  2  |  3  | \n");
        sb.append(" |_____|_____|_____| \n");
        sb.append("--> Para jogar digite o número correspondente ao campo representado acima.\n");
        return sb.toString();
    }

    @Override
    public int first() throws RemoteException {
        Random random = new Random();
        return random.nextInt(1);
    }

    @Override
    public String jogaCPU() throws RemoteException {
        Random random = new Random();
        int op = 0;
        do{op = random.nextInt(8);}while(vet[op] != " ");
        vet[op] = "X";
        return printJogo();
    }

    @Override
    public int endGame() throws RemoteException {
        String op = new String();
        
        if(vet[7] == vet[6] && vet[6] == vet[8] && vet[6] != " ")op = vet[6]; 
        if(vet[3] == vet[4] && vet[4] == vet[5] && vet[4] != " ")op = vet[4]; 
        if(vet[0] == vet[1] && vet[1] == vet[2] && vet[1] != " ")op = vet[1]; 
        if(vet[6] == vet[3] && vet[3] == vet[0] && vet[3] != " ")op = vet[3]; 
        if(vet[7] == vet[4] && vet[4] == vet[1] && vet[4] != " ")op = vet[4]; 
        if(vet[8] == vet[5] && vet[5] == vet[2] && vet[5] != " ")op = vet[5]; 
        if(vet[6] == vet[4] && vet[4] == vet[2] && vet[4] != " ")op = vet[4]; 
        if(vet[8] == vet[4] && vet[4] == vet[0] && vet[4] != " ")op = vet[4]; 
        
        if(op == "X"){
            return 2;
        }else if(op == "O"){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int jogada(int jogada) throws RemoteException {
        if(vet[jogada] == " "){
            vet[jogada] = "O";
        }else{
            return 0;
        }
        return jogada;
    }
}
