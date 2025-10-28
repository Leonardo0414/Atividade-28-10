public class Cliente {
    private static int i = 1;
    private int id;
    private String nome;
    private String email;

    public Cliente(String nome, String email) {
        this.id = i++;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Cliente ID: %d | Nome: %s | Email: %s", id, nome, email);
    }
}