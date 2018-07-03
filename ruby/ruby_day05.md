## CRUD basic code

```ruby
movies = {
  StarWars: 4.8, 
  Divergent: 4.7
  }

puts "What would you like to do? "

choice = gets.chomp

case choice
when "add"
  puts "What movie would you like to add? "
  title = gets.chomp
  if movies[title.to_sym].nil? 
    puts "What rating does the movie have? "
    rating = gets.chomp
    movies[title.to_sym] = rating.to_i
  else
    puts "That movie already exists! Its rating is #{movies[title.to_sym]}."
  end
when "update"
  puts "What movie would you like to update? "
  title = gets.chomp
  if movies[title.to_sym].nil? 
    puts "That movie does not exist."
  else
    puts "What is the new rating? "
    rating = gets.chomp
    movies[title.to_sym] = rating.to_i
  end
when "display"
  movies.each do |title, rating| 
    puts "#{title}: #{rating}"
  end
when "delete"
  puts "What movie would you like to delete?"
  title = gets.chomp
  if movies[title.to_sym].nil? 
    puts "That movie does not exist."
  else
    movies.delete(title.to_sym)
  end
else
  puts "Error!"
end
```



### if/unless

```ruby
ruby_is_eloquent = true
ruby_is_ugly = false

puts "Ruby is eloquent!" if ruby_is_eloquent
puts "Ruby's not ugly!" unless ruby_is_ugly
```



### Conditional Assignment

```ruby
favorite_book = nil
puts favorite_book #

favorite_book ||= "Cat's Cradle"
puts favorite_book #Cat's Cradle

favorite_book ||= "Why's (Poignant) Guide to Ruby"
puts favorite_book #Cat's Cradle
```



### Ternary operator

```ruby
def multiple_of_three(n)
  n % 3 == 0 ? "True" : "False" #return문 생략 가능
end
```



### Short-Circuit Evaluation

```ruby
def a
  puts "A was evaluated!"
  return true
end

def b
  puts "B was also evaluated!"
  return true
end

puts a || b
#A was evaluated!
#true
puts "------"
puts a && b
#A was evaluated!
#B was also evaluated!
#true

# Up the Down Staircase	
"L".upto("P") do |letter|
  puts letter
end
```



### Call and Response

`.respond_to?` takes a symbol and returns `true` if an object can receive that method and `false`otherwise 

```ruby
[1, 2, 3].respond_to?(:push) #true
[1, 2, 3].respond_to?(:to_sym) #false

age = 26
age.respond_to?(:next) #true
```



### Being pussy

```ruby
alphabet = ["a", "b", "c"]
alphabet.push("d")
alphabet += ["d"]
alphabet << "d"

caption = "A giraffe surrounded by "
caption += "weezards!"
caption << "weezards!"
```

