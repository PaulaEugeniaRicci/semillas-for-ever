package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class ParcelasTest : DescribeSpec ({
    describe("Creacion de parcela") {
        val soja = Soja(1.5, 2021)
        val parcela = ParcelaEcologica(20.0, 1.0, 10, mutableListOf(soja, soja, soja, soja))

        it("Superficie y cantidad maxima") {
            parcela.superficie().shouldBe(20)
            parcela.cantidadMaxima().shouldBe(4)
        }
        it ("Tiene o no complicaciones"){
            parcela.tieneComplicaciones().shouldBeFalse()
        }
        it ("Plantas agregadas"){
            shouldThrowAny {
                parcela.plantar(soja)
            }
            parcela.listaPlantas.shouldContainExactly(soja, soja, soja, soja)
        }
    }
    describe("Asociacion de plantas") {
        val menta = Menta(1.5, 2021)
        val soja = Soja(2.0, 2021)
        val parcelaEcologica = ParcelaEcologica(20.0, 1.0, 5, mutableListOf(menta))
        val parcelaIndustrial = ParcelaIndustrial(20.0, 1.0, 5, mutableListOf(menta, soja))

        it("Verificar asociaciones"){
            parcelaEcologica.seAsociaBien(menta).shouldBeTrue()
            parcelaIndustrial.seAsociaBien(soja).shouldBeTrue()
        }
    }
})

