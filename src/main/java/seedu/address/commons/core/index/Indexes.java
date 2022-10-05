package seedu.address.commons.core.index;

import java.util.HashSet;
import java.util.List;

import seedu.address.commons.exceptions.IllegalIndexException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents a unique collection of Index objects.
 */
public class Indexes {
    private HashSet<Index> indexes;

    /**
     * Constructs a new Indexes object given an array of strings.
     *
     * @param unParsedIndexes The array of strings that is going to be parsed.
     * @throws ParseException If there is a string from unParsedIndexes that can't be parsed.
     */
    public Indexes(String[] unParsedIndexes) throws ParseException {
        indexes = new HashSet<>();

        for (String arg : unParsedIndexes) {
            indexes.add(ParserUtil.parseIndex(arg));
        }
    }

    public UniquePersonList getAllPersonsFromIndexes(List<Person> personList)
            throws IllegalIndexException {
        UniquePersonList selectedPersons = new UniquePersonList();

        for (Index selectedIndex : indexes) {
            if (selectedIndex.getZeroBased() >= personList.size()) {
                throw new IllegalIndexException();
            }

            Person currentPerson = personList.get(selectedIndex.getZeroBased());
            selectedPersons.add(currentPerson);
        }

        return selectedPersons;
    }
}
