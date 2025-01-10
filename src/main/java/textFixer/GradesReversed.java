package textFixer;

public class GradesReversed {

    private String gradeStringToInt(String grade) {
        switch (grade) {
            case "Безупречно": return "5";
            case "Потрясающе": return "4";
            default: return "1";
        }
    }

    public String serializeGrades(String[] grades) {
        String gradeString = "";
        for (String grade : grades) {
            String[] arrStr = grade.split(" ");
            String intGrade = gradeStringToInt(arrStr[4]);
            String join = String.join(",", arrStr[0], arrStr[1], arrStr[2], intGrade);
            gradeString.join(";", join);

        }
        return gradeString;
    }

}

