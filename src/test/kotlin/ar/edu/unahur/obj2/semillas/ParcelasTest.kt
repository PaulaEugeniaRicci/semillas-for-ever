package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class ParcelasTest : DescribeSpec ({
    describe("Creacion de parcela") {
        val soja = Soja(1.5, 2021)
        val menta = Menta(1.0, 2021)
        val parcela = Parcela(20.0, 1.0, 10, mutableListOf(soja, soja, soja, soja))

        it("prueba de superficie y cantidad maxima") {
            parcela.superficie().shouldBe(20)
            parcela.cantidadMaxima().shouldBe(4)
        }

        it ("prueba de complicaciones"){
            parcela.tieneComplicaciones().shouldBeFalse()
        }
        it ("prueba de metodo plantar"){
            shouldThrowAny {
                parcela.plantar(soja)
            }
            parcela.plantas.shouldContainExactly(soja, soja, soja, soja)
        }
    }
})
