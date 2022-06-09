/**
 * Enumeration of card's catogries
 */
public enum Category {

    ANIMAL("Animal", "Animals roaming the land"),
    MACHINE("Robot", "Robots from the far future"),
    MAGICAL_CREATURE("Magical Creature", "Magical creature from the mystical plane"),
    PLANT("Plant", "Awoken plant that defend themselves");
    private final String name;
    private final String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", title='" + description + '\'' +
                '}';
    }

    Category(String name, String title) {
        this.name = name;
        this.description = title;
    }
}
