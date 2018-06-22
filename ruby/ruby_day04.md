# Splat!

Speaking of not knowing what to expect: your methods not only don't know what arguments they're going to get ahead of time but occasionally, they don't even know how many arguments there will be.

Let's say you have a method, `friend`, that `puts` the argument it receives from the user. It might look something like this:

```
def friend(name):
  puts "My friend is " + name + "."
end
```

This is great for just one friend, but what if you want to print out the all of the user's friends, without knowing how many friend names the user will put in ahead of time?

The solution: *splat arguments*. Splat arguments are arguments preceded by a `*`, which tells the program that the method can receive one or more arguments.



```ruby
def what_up(greeting, *friends)
  friends.each { |friend| puts "#{greeting}, #{friend}!" }
end

what_up("What up", "Ian", "Zoe", "Zenas", "Eleanor")
```





```ruby
# method that capitalizes a word
def capitalize(string) 
  puts "#{string[0].upcase}#{string[1..-1]}"
end

capitalize("ryan") # prints "Ryan"
capitalize("jane") # prints "Jane"

# block that capitalizes each string in the array
["ryan", "jane"].each {|string| 
    puts "#{string[0].upcase}#{string[1..-1]}"} # prints "Ryan", then "Jane"
```

# The Combined Comparison Operator

We can also use a new operator called the *combined comparison operator* to compare two Ruby objects. The combined comparison operator looks like this: `<=>`. It returns `0` if the first *operand* (item to be compared) equals the second, `1` if the first operand is greater than the second, and `-1` if the first operand is less than the second.

A block that is passed into the `sort`method must return either`1`, `0`, or `-1`. It should return -1 if the first block parameter should come before the second, `1` if vice versa, and `0` if they are of equal weight, meaning one does not come before the other (*i.e.* if two values are equal)

```ruby
books = ["Charlie and the Chocolate Factory", "War and Peace", "Utopia", "A Brief History of Time", "A Wrinkle in Time"]

# To sort our books in ascending order, in-place
books.sort! { |firstBook, secondBook| firstBook <=> secondBook }

# Sort your books in descending order, in-place below

books.sort! { |firstBook, secondBook| secondBook <=>  firstBook}
```



# Dare to Compare

We mentioned that hash lookup is faster with symbol keys than with string keys. Here, we'll prove it!

The code in the editor uses some new syntax, so don't worry about understanding all of it just yet. It builds two alphabet hashes: one that pairs string letters with their place in the alphabet ( "a" with 1, "b" with 2...) and one that uses symbols (`:a` with 1, `:b` with 2...). We'll look up the letter "r" 100,000 times to see which process runs faster!

It's good to keep in mind that the numbers you'll see are only fractions of a second apart, and we did the hash lookup *100,000 times* each. It's not much of a performance increase to use symbols in this case, but it's definitely there!



```ruby
require 'benchmark'

string_AZ = Hash[("a".."z").to_a.zip((1..26).to_a)]
symbol_AZ = Hash[(:a..:z).to_a.zip((1..26).to_a)]

string_time = Benchmark.realtime do
  100_000.times { string_AZ["r"] }
end

symbol_time = Benchmark.realtime do
  100_000.times { symbol_AZ[:r] }
end

puts "String time: #{string_time} seconds."
#String time: 0.008597373962402344 seconds.

puts "Symbol time: #{symbol_time} seconds."
#Symbol time: 0.005425076931715012 seconds.
```

