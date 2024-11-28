package Hotel.Hotel

class Hotel(val nome: String) {
     val quartos: BooleanArray = BooleanArray(20) { false }
     val hospedes: MutableList<Hospede> = mutableListOf()

    fun inicio() {
        while (true) {
            println("\nBem-vindo ao $nome!")
            println("Selecione o menu:")
            println("1. Reserva de Quartos.")
            println("2. Cadastro de Hóspedes.")
            println("3. Pesquisar Hóspedes.")
            println("4. Eventos.")
            println("5. Posto de Gasolina.")
            println("6. Manutenção do Ar.")
            println("7. Sair.")

            val option = readLine()?.toIntOrNull() ?: 0

            when (option) {
                1 -> reserva()
                2 -> cadastrarHospedes()
                3 -> pesquisarHospedes()
                4 -> eventos()
                5 -> postoGasolina()
                6 -> manutencaoAr()
                7 -> {
                    println("Muito obrigado e até logo!")
                    return
                }
                else -> println("Opção inválida. Tente novamente.")
            }
        }
    }

     fun reserva() {
        println("Qual o valor da diária?")
        val valorDaDiaria = readLine()?.toIntOrNull() ?: return

        println("Quantos dias seria a reserva?")
        val dias = readLine()?.toIntOrNull() ?: return

        println("O valor de $dias dias de hospedagem é R$${valorDaDiaria * dias}")

        var quartoEscolhido: Int? = null

        while (quartoEscolhido == null) {
            println("Qual é o quarto para reserva? (1-20)")
            val quarto = readLine()?.toIntOrNull()

            if (quarto == null || quarto < 1 || quarto > 20) {
                println("Número do quarto errado!")
                continue
            }

            if (quartos[quarto - 1]) {
                println("Este quarto já está ocupado.")
            } else {
                quartoEscolhido = quarto
                println("Quarto Livre!")
            }
        }

        println("Hospedagem confirmada para o quarto $quartoEscolhido por $dias dias.")
        val confirmacao = readLine()?.uppercase()

        if (confirmacao == "S") {
            quartos[quartoEscolhido!! - 1] = true
            println("Reserva efetuada com sucesso.")
        } else {
            println("Reserva cancelada.")
        }
    }

     fun cadastrarHospedes() {
        println("Qual o valor da diária?")
        val valor = readLine()?.toIntOrNull() ?: return

        var total = 0.0
        var gratuidade = 0
        var meiaEntrada = 0

        while (true) {
            println("Qual o nome do hóspede? (Digite 'Pare' para sair)")
            val nome = readLine()

            if (nome.equals("Pare", ignoreCase = true)) break

            println("Qual a idade do hóspede?")
            val idade = readLine()?.toIntOrNull() ?: continue

            when {
                idade < 6 -> {
                    println("$nome é gratuito.")
                    gratuidade++
                }
                idade >= 60 -> {
                    println("$nome paga meia-entrada.")
                    total += valor / 2
                    meiaEntrada++
                }
                else -> {
                    total += valor
                }
            }
            println("$nome cadastrado com sucesso.")
        }

        println("O valor total das hospedagens é R$ $total (gratuidade: $gratuidade, meia-entrada: $meiaEntrada).")
    }

     fun pesquisarHospedes() {
        println("Digite o nome do hóspede a ser pesquisado:")
        val nome = readLine()

        val encontrado = hospedes.find { it.nome.equals(nome, ignoreCase = true) }
        if (encontrado != null) {
            println("Hóspede encontrado: $encontrado")
        } else {
            println("Hóspede não encontrado.")
        }
    }

    fun eventos() {
        println("Quantos convidados para o evento?")
        val convidados = readLine()?.toIntOrNull() ?: return

        if (convidados <= 0 || convidados > 350) {
            println("Número de convidados inválido.")
            return
        }

        val auditório = if (convidados <= 150) "Auditório Laranja" else "Auditório Colorado"
        println("Use o $auditório para o evento.")

        println("Qual o dia do evento?")
        val dia = readLine()

        println("Qual o horário do evento?")
        val hora = readLine()?.toIntOrNull() ?: return

        println("Qual a duração do evento em horas?")
        val duracao = readLine()?.toIntOrNull() ?: return

        val garçons = (convidados / 12) + (duracao / 2)
        val custoGarçons = garçons * 10.50
        println("Serão necessários $garçons garçons. Custo: R$$custoGarçons")
    }

     fun postoGasolina() {
        println("Qual o valor do álcool no posto Wayne Oil?")
        val alcoolWayne = readLine()?.toDoubleOrNull() ?: return

        println("Qual o valor da gasolina no posto Wayne Oil?")
        val gasolinaWayne = readLine()?.toDoubleOrNull() ?: return

        println("Qual o valor do álcool no posto Stark Petrol?")
        val alcoolStark = readLine()?.toDoubleOrNull() ?: return

        println("Qual o valor da gasolina no posto Stark Petrol?")
        val gasolinaStark = readLine()?.toDoubleOrNull() ?: return

        val custoWayne = if (alcoolWayne <= gasolinaWayne * 0.7) alcoolWayne * 42 else gasolinaWayne * 42
        val custoStark = if (alcoolStark <= gasolinaStark * 0.7) alcoolStark * 42 else gasolinaStark * 42

        val postoBarato = if (custoWayne < custoStark) "Wayne Oil" else "Stark Petrol"
        println("É mais barato abastecer no posto $postoBarato.")
    }

    fun manutencaoAr() {
        println("Informe o número do quarto para manutenção do ar condicionado:")
        val numeroQuarto = readLine()?.toIntOrNull() ?: return

        if (numeroQuarto in 1..20) {
            println("Manutenção agendada para o quarto $numeroQuarto.")
        } else {
            println("Número de quarto inválido. Escolha entre 1 e 20.")
        }
    }
}

data class Hospede(val nome: String, val idade: Int)

fun main() {
    val hotel = Hotel("Hotel Tinny")
    hotel.inicio()
}
