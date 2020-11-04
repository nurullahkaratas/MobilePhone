import java.util.ArrayList;

public class Phone {
    private String myNumber;
    private ArrayList<Contact> myContactsList;

    public Phone(String myNumber) {
        this.myNumber = myNumber;
        this.myContactsList = new ArrayList<Contact>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            System.out.println("Contact is already on file");
            return false;
        }
        this.myContactsList.add(contact);
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        if (findContact(oldContact) < 0) {
            System.out.println("Contact was not found");
            return false;
        } else if (findContact(newContact) != -1) {
            System.out.println("Update cannot successful cause new name already in file ");
            return false;
        }
        this.myContactsList.set(findContact(oldContact), newContact);
        System.out.println(oldContact.getName() + " was replaced with " + newContact.getName());
        return true;

    }

    public boolean removeContact(Contact contact) {
        int foundPosition = findContact(contact);
        if (foundPosition < 0) {
            System.out.println("Contact can not removed ,was not found");
            return false;
        }
        this.myContactsList.remove(foundPosition);
        System.out.println(contact.getName() + " Contact deleted");
        return true;
    }

    public String queryContact(Contact contact) {
        if (findContact(contact) >= 0) {
            return contact.getName();
        }
        return null;
    }

    public Contact queryContact(String name) {
        if (findContact(name) >= 0) {
            return myContactsList.get(findContact(name));
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Contact list...");
        for (int i = 0; i < myContactsList.size(); i++) {
            System.out.println(i + ".   --> " + myContactsList.get(i).getName()
                    + " -> " + myContactsList.get(i).getPhoneNumber());
        }
    }


    private int findContact(String name) {
        for (int i = 0; i < myContactsList.size(); i++) {
            Contact contact = this.myContactsList.get(i);
            if (contact.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private int findContact(Contact contact) {
        return myContactsList.indexOf(contact);//found position
    }
}
