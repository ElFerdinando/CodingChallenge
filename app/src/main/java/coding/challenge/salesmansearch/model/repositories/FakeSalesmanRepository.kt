package coding.challenge.salesmansearch.model.repositories

import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.model.allSampleSalesman

class FakeSalesmanRepository : SalesmanRepository {
    override fun getSalesman(postcodeExpression: String): List<Salesman> {
        val result = mutableListOf<Salesman>()
        allSampleSalesman.forEach() { salesman: Salesman ->
            if(postcodeExpressionMatchesSalesman(salesman = salesman, query = postcodeExpression)){
                result.add(salesman)
            }
        }
        return result
    }

    /**
     * this function goes through the list off postCodes of a salesman an checks wether the query
     * matches at least one of them
     * @return true if one match is found
     */
    private fun postcodeExpressionMatchesSalesman(salesman: Salesman, query: String): Boolean {
        salesman.areas.forEach(){postcodeExpression : String ->
            if(postcodeExpressionsMatches(postcodeExpression, query)){
                return true
            }
        }
        return false
    }

    /**
     * this function checks if two postcode expressions match each other
     * since both can contain asterisks we have to check both directions
     * @return true if one matches the other
     */
    private fun postcodeExpressionsMatches(postcode1: String, postcode2: String): Boolean {
        return postcode1.matches(postcode2.replace("*", ".*").toRegex()) ||
                postcode2.matches(postcode1.replace("*", ".*").toRegex())
    }
}