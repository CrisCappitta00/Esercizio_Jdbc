package com.azienda.jdbcEsercizio.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.azienda.jdbcEsercizio.businessLogic.AutomobileServices;
import com.azienda.jdbcEsercizio.dao.AutomobileDao;
import com.azienda.jdbcEsercizio.model.Automobile;

public class MainAuto {

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try (Scanner sc = new Scanner(System.in)){
			// connesione db aperta
			String driverName = "com.mysql.cj.jdbc.Driver";
			String dbUrl = "jdbc:mysql://localhost:3306/eserciziojdbc?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String dbUser = "root";
			String dbPassword = "082118";

			Class.forName(driverName);
			con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			con.setAutoCommit(false);
			AutomobileDao autodao = new AutomobileDao(con);
			AutomobileServices services = new AutomobileServices(autodao, con);

			while(true) {
				
				
				System.out.println("\n=== MENU ===");
				System.out.println("1) Inserisci automobile");
				System.out.println("2) Cancella per marca (prefisso)");
				System.out.println("3) Aggiorna targa per marca (prefisso)");
				System.out.println("4) Visualizza tutte le auto");
				System.out.println("5) Esci");
				System.out.print("Scelta: ");

				int scelta = Integer.parseInt(sc.nextLine());
				switch (scelta) {
				case 1:
					System.out.print("Marca: ");
					String marca = sc.nextLine();
					System.out.print("Modello: ");
					String modello = sc.nextLine();
					System.out.print("Colore: ");
					String colore = sc.nextLine();
					System.out.print("Cilindrata: ");
					Float cilindrata = Float.parseFloat(sc.nextLine());
					System.out.print("Targa: ");
					String targa = sc.nextLine();

					try {
						services.insertAuto(marca, modello, colore, cilindrata, targa);
						System.out.println("Inserita!");
					} catch (Exception e) {
						System.out.println("Errore: " + e.getMessage());
					}
					break;
				case 2:
					System.out.print("Prefisso marca da cancellare: ");
					String pref = sc.nextLine();
					try {
						services.deleteAuto(pref);
						System.out.println("Auto cancellate!");
					} catch(Exception e) {
						System.out.println("Errore: " + e.getMessage());
					}
					break;
				case 3:
					System.out.print("Prefisso marca da aggiornare: ");
					String prefUpdate = sc.nextLine();
					System.out.print("Nuova targa: ");
					String nuovaTarga = sc.nextLine();
					try {
						services.updateTargaByMarcaPrefix(prefUpdate, nuovaTarga);
						System.out.println("Aggiornamento completato!");
					} catch(Exception e) {
						System.out.println("Errore: " + e.getMessage());
					}
					break;
				case 4:
					try {
						for(Automobile a : services.getAll()) {
							System.out.println(a);
						}
					} catch(Exception e) {
						System.out.println("Errore: " + e.getMessage());
					}
					break;
				case 5:
					System.out.println("Uscita...");
					return;
				default:
					System.out.println("Scelta non valida, riprova!");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (con != null) con.close();
		}

	}

}
