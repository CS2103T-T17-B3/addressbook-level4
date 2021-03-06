package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 * {@code ParserUtil} contains methods that take in {@code Optional} as parameters. However, it goes against Java's
 * convention (see https://stackoverflow.com/a/39005452) as {@code Optional} should only be used a return type.
 * Justification: The methods in concern receive {@code Optional} return values from other methods as parameters and
 * return {@code Optional} values based on whether the parameters were present. Therefore, it is redundant to unwrap the
 * initial {@code Optional} before passing to {@code ParserUtil} as a parameter and then re-wrap it into an
 * {@code Optional} return value inside {@code ParserUtil} methods.
 */
public class    ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INSUFFICIENT_PARTS = "Number of parts must be more than 1.";
    public static final String MESSAGE_INVALID_SORT = "Invalid Sorting type.";
    public static final String MESSAGE_INVALID_ARGUMENTS = "Incorrect number of arguments.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws IllegalValueException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws IllegalValueException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new IllegalValueException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**

     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws IllegalValueException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static ArrayList<Index> parseIndexes(String manyIndex) throws IllegalValueException {
        ArrayList<Index> listOfIndex = new ArrayList<>();
        String trimmedIndex = manyIndex.trim();
        String[] indexes = trimmedIndex.split("\\s+");
        for (String index : indexes) {
            if (!StringUtil.isNonZeroUnsignedInteger(index)) {
                throw new IllegalValueException(MESSAGE_INVALID_INDEX);
            }
            listOfIndex.add(Index.fromOneBased(Integer.parseInt(index)));
        }
        return listOfIndex;
    }

    //@@author mavistoh
    /**
     * Parses {@code oneBasedIndex} into an {@code Index[]} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws IllegalValueException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index[] parseDeleteIndex(String oneBasedIndex) throws IllegalValueException {
        String[] parts = oneBasedIndex.split(",");
        String[] trimmedIndex = new String[parts.length];
        int[] trimmedIntIndex = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            trimmedIndex[i] = parts[i].trim();
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex[i])) {
                throw new IllegalValueException(MESSAGE_INVALID_INDEX);
            }
        }

        for (int i = 0; i < trimmedIndex.length; i++) {
            trimmedIntIndex[i] = Integer.parseInt(trimmedIndex[i]);
        }

        return Index.arrayFromOneBased(trimmedIntIndex);
    }

    /**
     * Parses a {@code Optional<String> name} into an {@code Optional<Name>} if {@code name} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Name> parseName(Optional<String> name) throws IllegalValueException {
        return name.isPresent() ? Optional.of(new Name(name.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> phone} into an {@code Optional<Phone>} if {@code phone} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Phone> parsePhone(Optional<String> phone) throws IllegalValueException {
        return phone.isPresent() ? Optional.of(new Phone(phone.get())) : Optional.empty();
    }

    //@@author mavistoh
    /**
     * Parses a {@code Optional<String> birthday} into an {@code Optional<Birthday>} if {@code birthday} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Birthday> parseBirthday(Optional<String> birthday) throws IllegalValueException {
        return birthday.isPresent() ? Optional.of(new Birthday(birthday.get())) : Optional.empty();
    }
    //@@author

    /**
     * Parses a {@code Optional<String> address} into an {@code Optional<Address>} if {@code address} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Address> parseAddress(Optional<String> address) throws IllegalValueException {
        return address.isPresent() ? Optional.of(new Address(address.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> email} into an {@code Optional<Email>} if {@code email} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Email> parseEmail(Optional<String> email) throws IllegalValueException {
        return email.isPresent() ? Optional.of(new Email(email.get())) : Optional.empty();
    }

    //@@author eldonng
    /**
     * Parses a {@code Optional<String> name} into an {@code Optional<Name>} if {@code name} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<GroupName> parseGroupName(Optional<String> name) throws IllegalValueException {
        requireNonNull(name);
        return name.isPresent() ? Optional.of(new GroupName(name.get())) : Optional.empty();
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws IllegalValueException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        return tagSet;
    }

    //@@author LimeFallacie
    /**
     * Parses a String and checks for validity. Leading and trailing whitespaces will be removed
     * @throws IllegalValueException if specified string is invalid (not 1 of 3 options)
     */
    public static String parseSortType(String sortType) throws IllegalValueException {
        String trimmedSortType = sortType.trim();
        switch (trimmedSortType) {
        case "name":
        case "phone":
        case "email":
            return trimmedSortType;
        default:
            throw new IllegalValueException(MESSAGE_INVALID_SORT);
        }
    }

    //@@author eldonng
    /**
     * Parses a String and checks whether it has two different .
     */
    public static String[] parseColourCommand(String args) throws IllegalValueException {
        String[] splitArgs = args.trim().split("\\s+");
        if (splitArgs.length == 2) {
            return splitArgs;
        }
        throw new IllegalValueException(MESSAGE_INVALID_ARGUMENTS);
    }

    //@@author LimeFallacie
    /**
     * Parses a String argument for tag. Leading and trailing whitespaces will be removed
     */
    public static String parseTag(String tag) {
        String trimmedTag = tag.trim();
        return trimmedTag;

    }

    /**
     * Parses a String argument for a file path destination for Export.
     * Leading and trailing whitespaces will be removed
     */
    public static String parseFilePath(String path) {
        String trimmedPath = path.trim();
        return trimmedPath;
    }
}
