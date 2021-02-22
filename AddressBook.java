import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Scanner sc = null;
		String name, address, phone;
		sc = new Scanner(System.in);
		ArrayList<Contact>addressBook = new ArrayList<Contact>();
		File f = new File("contacts.txt");
		if(f.exists()) {
			Scanner readFile = new Scanner(f);
			while (readFile.hasNextLine()) {
				String str = readFile.nextLine();
				String arr[] = str.split(":");
				Contact c = new Contact(arr[0],arr[1],arr[2]);
				addressBook.add(c);
			}
			readFile.close();
		} else {
			f.createNewFile();
		}
		while (true) {
			displayMenu();
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				while (true) {
					System.out.print("Enter name :");
					name = sc.next();
					int index = isAlreadyExists(name, addressBook);
					if (index != -1) {
						System.out.println("-> Name Already exists <-");
					} else {
						sc.nextLine();
						System.out.print("Enter address :");
						address = sc.nextLine();
						System.out.print("Enter phone number :");
						phone = sc.next();
						Contact c = new Contact(name, address, phone);
						addressBook.add(c);
						break;
					}
				}
				continue;
			}
			case 2: {
				System.out.print("Enter name :");
				name = sc.next();
				int index = isAlreadyExists(name, addressBook);
				if (index != -1) {
					sc.nextLine();
					System.out.print("Enter address :");
					address = sc.nextLine();
					System.out.print("Enter phone number :");
					phone = sc.next();
					addressBook.get(index).setAddress(address);
					addressBook.get(index).setPhone_number(phone);
					System.out.println("->Successfully updated<-");
				}
				continue;			
			}
			case 3:
				System.out.print("Enter name :");
				name = sc.next();
				int index = isAlreadyExists(name, addressBook);
				if (index != -1) {
					addressBook.remove(index);
					System.out.println("->Successfully removed<-");
				} else {
					System.out.println("->Contact not found<-");
				}				
				continue;
			case 4: {
				sortByName(addressBook);
				display(addressBook);
				continue;
			}
			case 5: {
				System.out.print("Enter a keyword to search :");
				String key = sc.next();
				int index1 = isFound(addressBook,key);
				if (index1 == -1) {
					System.out.println("->Contact not found<-");
				} else {
					System.out.println(addressBook.get(index1));
				}
				continue;
			}
			case 6: {
				FileWriter fw = new FileWriter(f);
				for(int i = 0; i < addressBook.size(); i++) {	
			fw.write(addressBook.get(i).getName()+":"+ addressBook.get(i).getAddress()+":"+addressBook.get(i).getPhone_number()+"\n");
				}
				fw.close();
				break;
			}
			default: {
				System.out.println("-->Invalid Choice<--");
				continue;
			}
		    }
			break;
		}
	}
			private static int isFound(ArrayList<Contact>addressBook, String key) {
				for (int i = 0; i < addressBook.size(); i++) {
					if (addressBook.get(i).getName().contains(key) || addressBook.get(i).getAddress().contains(key) || addressBook.get(i).getPhone_number().contains(key)) {
						return i;
					}
				}
				return -1;
			}
			private static void display(ArrayList<Contact>addressBook) {
				System.out.println("Displaying the list of contacts :");
				for (int i = 0; i < addressBook.size(); i++) {
					System.out.println(addressBook.get(i));
				}
			}
			private static void sortByName(ArrayList<Contact>addressBook) {
				Contact temp;
				for (int i = 0; i < addressBook.size(); i++) {
					for (int j = i+1; j < addressBook.size(); j++) {
						if (addressBook.get(i).getName().compareTo(addressBook.get(j).getName()) > 0) {
							temp = addressBook.get(i);
							addressBook.set(i, addressBook.get(j));
							addressBook.set(j, temp);
						}
					}
				}
			}
			private static int isAlreadyExists(String name, ArrayList<Contact>addressBook) {
				for (int i = 0; i < addressBook.size(); i++) {
					if (addressBook.get(i).getName().equalsIgnoreCase(name))
						return i;
				}
				return -1;
			}
			private static void displayMenu() {
				System.out.println("\n1.Add a new contact");
				System.out.println("2.Update an existing contact");
				System.out.println("3.Delete a contact");
				System.out.println("4.List all added contacts in sorted order");
				System.out.println("5.Search for a contact");
				System.out.println("6.Quit");
				System.out.println("Enter Choice:");
			}	
}