package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({
    describe("Creación de las plantas") {
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val peperina = Peperina (0.3, 2021)
        val soja = Soja(0.6, 2009)
        val quinoa = Quinoa (5.0,2010, 0.2)
        val quinoa2 = Quinoa (5.0,2006, 0.9)
        val sojaTransgenica = SojaTransgenica (0.8, 2008)

        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioObtencion.shouldBe(2021)
        }
        it("verificar si da semillas") {
            menta.daSemillas().shouldBeTrue()
            mentita.daSemillas().shouldBeFalse()
            soja.daSemillas().shouldBeFalse()
            quinoa.daSemillas().shouldBeTrue()
            quinoa2.daSemillas().shouldBeTrue()
            sojaTransgenica.daSemillas().shouldBeFalse()
        }
        it("tolerancia al sol"){
            soja.toleranciaAlSol().shouldBe(8)
        }
        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
        }
        it("espacio") {
            menta.espacioOcupado().shouldBe(2.0)
            mentita.espacioOcupado().shouldBe(1.3)
            peperina.espacioOcupado().shouldBe(2.6)
            soja.espacioOcupado().shouldBe(0.3)
        }
        it("verifico la suma de varias") {
            val superficie = mutableListOf(
                soja.espacioOcupado(),
                menta.espacioOcupado(),
                mentita.espacioOcupado()
            ).sum()
            Math.ceil(superficie).shouldBe(4)
        }
    }
    describe("Prueba de parcelas ideales") {
        val menta = Menta(1.0, 2021)
        val soja = Soja(0.6, 2009)
        val quinoa = Quinoa (5.0,2010, 0.2)
        val sojaTransgenica = SojaTransgenica (0.8, 2008)
        val parcela = Parcela(2.0, 4.0, 8, mutableListOf(soja), ParcelaIndustrial())
        val parcelaPequena = Parcela(2.5, 2.0, 8, mutableListOf(soja), ParcelaIndustrial())

        it("probamos cada variedad") {
            menta.esParcelaIdeal(parcela).shouldBeTrue()
            quinoa.esParcelaIdeal(parcela).shouldBeTrue()
            soja.esParcelaIdeal(parcela).shouldBeTrue()
            sojaTransgenica.esParcelaIdeal(parcelaPequena).shouldBeTrue()
        }
    }
})

