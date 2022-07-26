import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX = "\\s+";

    public String countFrequencyOfWords(String inputStr) {


        if (inputStr.split(REGEX).length == 1) {
            return inputStr + " 1";
        }


        try {

            List<Input> inputList=splitTheInputString(inputStr);

            List<Input> groupList = groupTheSameStrings(inputList);

            groupList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");

            groupList.forEach(input -> joiner.add(input.getValue() + " " + input.getWordCount()));

            return joiner.toString();

        } catch (Exception e) {
            return "Calculate Error";
        }
    }


    private List<Input> groupTheSameStrings(List<Input> inputList) {
        return new ArrayList<>(
            inputList.stream()
                    .collect(Collectors
                    .toMap(Input::getValue,
                    input -> input,
                    (first, second) -> {first.setCount(first.getWordCount() + second.getWordCount());
                    return first;
                    }))
                    .values());
    }
    private List<Input> splitTheInputString(String inputStr){

        return Arrays.stream(inputStr
                .split(REGEX))
                .map(str -> new Input(str, 1))
                .collect(Collectors.toList());
    }

}
