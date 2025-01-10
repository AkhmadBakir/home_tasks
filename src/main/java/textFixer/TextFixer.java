package textFixer;

public class TextFixer {

    //начинать строку с большой буквы.
    public String capitalize(String str) {
        // Реализуйте этот метод
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public String fixText(String text) {
        // Реализуйте этот метод
        String newText = text.replace('„', '«')
                .replace('“', '»')
                .replace("цевилизаций", "цивилизаций")
                .trim();
        return capitalize(newText);
    }

    public static void main(String[] args) {

        String text = "    история каждой из крупных галактических цевилизаций может быть разделена на три различные, " +
                "ярко выраженные фазы: Борьба за выживание, Любопытство и Утонченность, также именуемые фазами " +
                "„Как?“, „Зачем?“ и „Где?“. Пример: если для первой фазы характерен вопрос: „Как бы нам поесть?“," +
                " а для второй „Зачем мы едим?“, то третья отличается вопросом: „Где бы нам лучше поужинать?“.   ";

        var textFixer = new TextFixer();
        var fixedText = textFixer.fixText(text);
        System.out.println(fixedText);

        String[] data = {
                "Вероника Чехова физика — Безупречно",
                "Анна Строкова математика — Потрясающе",
                "Иван Петров геометрия — Безупречно"
        };

        System.out.println(serializeGrades(data));
    }
    private static String gradeStringToInt(String grade) {
        switch (grade) {
            case "Безупречно": return "5";
            case "Потрясающе": return "4";
            default: return "1";
        }
    }
/*
    public static String serializeGrades(String[] grades) {
        String gradeString;
        for (String grade : grades) {
            String[] arrStr = grade.split(" ");
            String intGrade = gradeStringToInt(arrStr[4]);
            String join = String.join(",", arrStr[0], arrStr[1], arrStr[2], intGrade);
            gradeString = String.join(";", join);

        }
        return gradeString;
    }
*/
    public static String serializeGrades(String[] grades) {
        StringBuilder gradeString = new StringBuilder();
        for (int i = 0; i < grades.length; i++) {
            String grade = grades[i];
            String[] arrStr = grade.split(" ");
            String intGrade = gradeStringToInt(arrStr[4]);
            String join = String.join(",", arrStr[0], arrStr[1], arrStr[2].replace("—", ""), intGrade);

            gradeString.append(join);
            if (i < grades.length - 1) {
                gradeString.append(";");
            }
        }
        return gradeString.toString();
    }

}