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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LUCAS MATHEUS
 */
public class JogoVelha extends UnicastRemoteObject implements JogoVelhaRemote
{   
    int jogando = 0;
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
        sb.append("  ========== J O G O ==========\n");
        sb.append(" |      _____ _____ _____      |\n");
        sb.append(" |     |     |     |     |     |\n");
        sb.append(" |     |  "+ vet[6] +"  |  "+ vet[7] +"  |  "+ vet[8] +"  |     |\n");
        sb.append(" |     |_____|_____|_____|     |\n");
        sb.append(" |     |     |     |     |     |\n");
        sb.append(" |     |  "+ vet[3] +"  |  "+ vet[4] +"  |  "+ vet[5] +"  |     |\n");
        sb.append(" |     |_____|_____|_____|     |\n");
        sb.append(" |     |     |     |     |     |\n");
        sb.append(" |     |  "+ vet[0] +"  |  "+ vet[1] +"  |  "+ vet[2] +"  |     |\n");
        sb.append(" |     |_____|_____|_____|     |\n");
        sb.append("  ========= V E L H A =========\n");
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
        sb.append("  ========== J O G O ==========\n");
        sb.append(" |      _____ _____ _____      |\n");
        sb.append(" |     |     |     |     |     |\n");
        sb.append(" |     |  7  |  8  |  9  |     |\n");
        sb.append(" |     |_____|_____|_____|     |\n");
        sb.append(" |     |     |     |     |     |\n");
        sb.append(" |     |  4  |  5  |  6  |     |\n");
        sb.append(" |     |_____|_____|_____|     |\n");
        sb.append(" |     |     |     |     |     |\n");
        sb.append(" |     |  1  |  2  |  3  |     |\n");
        sb.append(" |     |_____|_____|_____|     |\n");
        sb.append("  ========= V E L H A =========\n");
        sb.append("--> Para jogar digite o número correspondente ao campo representado acima.");
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
        op = " ";
        
        if(vet[7].equals(vet[6]) && vet[6].equals(vet[8]) && !" ".equals(vet[6]))op = vet[6];
        if(vet[3].equals(vet[4]) && vet[4].equals(vet[5]) && !" ".equals(vet[4]))op = vet[4];
        if(vet[0].equals(vet[1]) && vet[1].equals(vet[2]) && !" ".equals(vet[1]))op = vet[1];
        if(vet[6].equals(vet[3]) && vet[3].equals(vet[0]) && !" ".equals(vet[3]))op = vet[3];
        if(vet[7].equals(vet[4]) && vet[4].equals(vet[1]) && !" ".equals(vet[4]))op = vet[4];
        if(vet[8].equals(vet[5]) && vet[5].equals(vet[2]) && !" ".equals(vet[5]))op = vet[5];
        if(vet[6].equals(vet[4]) && vet[4].equals(vet[2]) && !" ".equals(vet[4]))op = vet[4];
        if(vet[8].equals(vet[4]) && vet[4].equals(vet[0]) && !" ".equals(vet[4]))op = vet[4];
        
        if("X".equals(op)){
            return 2;
        }else if("O".equals(op)){
            return 1;
        }else if(" ".equals(op) && checkLines()){
            return 3;
        }else{
            return 0;
        }
        
    }
    
    public boolean checkLines(){
        for(int i = 0; i < 9 ; i++){
            if(" ".equals(vet[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public int jogada(int jogada) throws RemoteException {
        if(" ".equals(vet[jogada])){
            vet[jogada] = "O";
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    synchronized public int getStatus() throws RemoteException {
        return this.jogando;
    }

    @Override
    synchronized public void setStatus(int status) throws RemoteException {
        this.jogando = status;
    }

    @Override
    public int jogadaMult(int jogada, String simbolo) throws RemoteException{
        if(" ".equals(vet[jogada])){
            vet[jogada] = simbolo;
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    public void initVetMult() throws RemoteException {
        initVet();
        this.jogando = 0;
    }

    @Override
    public void cleanVetMult() throws RemoteException {
        cleanVet();
        this.jogando = 0;
    }

    @Override
    public void fimJogada(String simbolo) throws RemoteException {
        
        if("O".equals(simbolo)){
            setStatus(2);
        }else{
            setStatus(1);                        
        }
    }
}
