package coding.challenge.salesmansearch.model.repositories

import coding.challenge.salesmansearch.model.Salesman

interface SalesmanRepository {
    fun getSalesman(postcodeExpression: String): List<Salesman>

}