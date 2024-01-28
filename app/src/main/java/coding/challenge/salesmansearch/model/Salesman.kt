package coding.challenge.salesmansearch.model

data class Salesman(
    val name: String,
    val areas: Set<String>
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        areas.forEach() { postcode ->
            if (postcodeExpressionsMatches(postcode, query)) return true
        }
        //no postcode in the area list matches the query
        return false
    }

    /**
     * this function checks if two postcode expressions match each other
     * since both can contain asterisks we have to check both directions
     */
    private fun postcodeExpressionsMatches(postcode1: String, postcode2: String): Boolean {
        return postcode1.matches(postcode2.replace("*", ".*").toRegex()) ||
                postcode2.matches(postcode1.replace("*", ".*").toRegex())
    }
}
