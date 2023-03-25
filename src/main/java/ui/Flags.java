package ui;

public class Flags {
    public enum Error {
        EMPTY_INPUT,
        DOUBLE_OVERFLOW,
        INTEGER_OVERFLOW,
        INVALID_INDEX,
        REQUIRE_INTEGER,
        REQUIRE_VALID_ITEM_INDEX,
        ITEM_DUPLICATE_NAME_ERROR,
        ITEM_NAME_MIN_LENGTH_ERROR,
        ITEM_NAME_MAX_LENGTH_ERROR,
        ITEM_PRICE_MIN_LENGTH_ERROR,
        ITEM_PRICE_NEGATIVE_ERROR,
        ITEM_PRICE_INVALID_DECIMAL_PLACE_ERROR,
        ITEM_PRICE_INVALID_FORMAT_ERROR,
        ITEM_PRICE_OVERFLOW_ERROR,
        ITEM_INDEX_INVALID_FORMAT_ERROR,
        ITEM_INDEX_OVERFLOW_ERROR,
        ITEM_INDEX_OUT_OF_BOUND_ERROR,
        INVALID_ADDITEM_FORMAT,
        INVALID_DELETEITEM_FORMAT,
        PRICE_DECIMAL_ERROR,
        INVALID_PRICE_ERROR,
        NO_SUCH_ITEM,
        MULTIPLE_SIMILAR_ITEMS,
        EMPTY_MENU,
        MISSING_ITEM_NAME_FLAG,
        MISSING_ITEM_PRICE_FLAG,
        MISSING_ITEM_INDEX_FLAG,
        MISSING_ITEM_NAME_OR_PRICE_FLAG,
        MISSING_ITEM_NAME_AND_PRICE_FLAG,
        MISSING_ORDER_FLAG,
        MISSING_ORDER_FLAG_ARGUMENT,
        MISSING_QUANTITY_FLAG_ARGUMENT,
        INVALID_ORDER_ITEM_INDEX_FORMAT,
        NEGATIVE_ORDER_ITEM_INDEX,
        INVALID_QUANTITY_FORMAT,
        INVALID_NEGATIVE_QUANTITY,
        INVALID_ORDER_ITEM_INDEX_OUT_OF_BOUNDS,
        INVALID_MULTIPLE_ORDER_FORMAT_EXCEPTION,
        MISSING_MULTIPLE_ORDER_ARGUMENT_EXCEPTION,
        MISSING_MULTIPLE_ORDER_FLAG_EXCEPTION,
        INVALID_PAY_TYPE,
        INVALID_PAYMENT_AMOUNT_FOR_CARD,
        INVALID_PAY_AMOUNT_NEGATIVE,
        INVALID_PAY_AMOUNT_FORMAT,
        INVALID_PAY_AMOUNT_DECIMAL_PLACE,
        INSUFFICIENT_PAY_AMOUNT,
        MISSING_PAY_TYPE_FLAG,
        MISSING_PAY_TYPE_ARGUMENT,
        MISSING_PAY_AMOUNT_FLAG,
        MISSING_PAY_AMOUNT_ARGUMENT,
        MISSING_PAY_COMMAND,
        MISSING_REFUND_ORDER_FLAG,
        MISSING_REFUND_ORDER_ARGUMENT,
        INVALID_REFUND_ORDER_ID,
        INVALID_REFUND_ORDER_TYPE
    }
}