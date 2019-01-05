@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

//import kotlin.math.min
//import kotlin.math.max
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    return if (n <= 1)
        1.0
    else
        n * factorial(n - 1)
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var num = n
    var rank = 0
    do {
        num /= 10
        rank++
    } while (num > 0)
    return rank
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    val fibRow = mutableListOf(1, 1, 2, 3, 5, 8)
    if (n < fibRow.count()) return fibRow[n - 1]
    else for (i in 7..n) {
        fibRow.add(fibRow[i - 2] + fibRow[i - 3])
    }
    return fibRow.last()
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = max(m, n)
    while (k < m * n) {
        if (k % m == 0 && k % n == 0)
            return k
        k += max(m, n)
    }
    return m * n

}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..(sqrt(n.toDouble()) + 1).toInt())
        if (n % i == 0)
            return i
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    val divisors = mutableSetOf(1)
    for (i in 2..(sqrt(n.toDouble()) + 1).toInt())
        if (n % i == 0) {
            divisors.add(i)
            if (n % (n / i) == 0)
                divisors.add(n / i)
        }
    var rez = 1
    for (x in divisors.sorted().reversed()) {
        if (rez * x < n)
            rez *= x
    }
    return rez
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    fun divSearch(n: Int): Set<Int> {
        val divisors = mutableSetOf(1)
        for (i in 2..(sqrt(n.toDouble()) + 1).toInt())
            if (n % i == 0) {
                divisors.add(i)
                if (n % (n / i) == 0)
                    divisors.add(n / i)
            }
        divisors.add(n)
        return divisors.sorted().toSet()
    }

    val mDivisors = divSearch(m)
    val nDivisors = divSearch(n)
    for (i in mDivisors)
        if (i != 1 && i in nDivisors)
            return false
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in 1..m.toLong()) {
        if ((i * i) in m..n)
            return true
        if (i * i > n)
            return false
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var steps = 0
    var myX = x
    while (myX != 1) {
        if (myX % 2 == 0)
            myX /= 2
        else
            myX = 3 * myX + 1
        steps++
    }
    return steps
}

/**
 * Средняя
 *
 * Для заданного myX рассчитать с заданной точностью eps
 * sin(myX) = myX - myX^3 / 3! + myX^5 / 5! - myX^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var power = 1
    var rez = 0.0
    var flag = 1
    val myX = x % (2 * PI)
    while (abs(myX.pow(power) / factorial(power)) >= eps) {
        rez += flag * myX.pow(power) / factorial(power)
        power += 2
        flag *= -1
    }
    return rez
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var power = 0
    var rez = 0.0
    var flag = 1
    val myX = x % (2 * PI)
    while (abs(myX.pow(power) / factorial(power)) >= eps) {
        rez += flag * myX.pow(power) / factorial(power)
        power += 2
        flag *= -1
    }
    return rez
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var x = n
    var rez = 0
    while (x > 0) {
        rez = rez * 10 + (x % 10)
        x /= 10
    }
    return rez
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var x = n
    val digits = mutableSetOf<Int>()
    while (x > 0) {
        digits.add(x % 10)
        x /= 10
    }
    return digits.size > 1
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var x = 1
    var i = 1

    while (true) {
        var tmp = x.toDouble().pow(2).toInt()

        var r = 0
        while (tmp > 0) {
            r++
            if (i == n) {
                tmp = x.toDouble().pow(2).toInt()
                var tr = 0
                while (tmp > 0) {
                    tmp /= 10
                    tr++
                }
                tmp = x.toDouble().pow(2).toInt()
                return (tmp / 10.toDouble().pow(tr - r).toInt()) % 10
            }
            i++
            tmp /= 10
        }
        x++
    }

}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var x = 1
    var i = 1

    while (true) {
        var tmp = fib(x)

        var r = 0
        while (tmp > 0) {
            r++
            if (i == n) {
                tmp = fib(x)
                var tr = 0
                while (tmp > 0) {
                    tmp /= 10
                    tr++
                }
                tmp = fib(x)
                return (tmp / 10.toDouble().pow(tr - r).toInt()) % 10
            }
            i++
            tmp /= 10
        }
        x++
    }
}
