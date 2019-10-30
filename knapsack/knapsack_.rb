# 1 (number of items chosen)
# 2 (indices chosen)
w = 6
n = 4
wt = [4, 4, 3, 2, 1]
val = [6, 5, 4, 3, 2]
puts "capacity #{w}"
puts "count #{n}"
puts "weights #{wt}"
puts "values #{val}"

mat = Array.new(n + 1){ Array.new(w + 1) }

(0..w).each { |i| 
    mat[0][i] = 0
}

(0..n).each { |i| 
    mat[i][0] = 0
}

(1..n).each { |item|
    (1..w).each { |capacity|
        max_val_without_current = mat[item - 1][capacity]
        max_val_with_current = 0
        weight_of_current = wt[item - 1]
        if capacity >= weight_of_current
            max_val_with_current = val[item - 1]
            remaining_capacity = capacity - weight_of_current
            max_val_with_current += mat[item - 1][remaining_capacity]
        end

        mat[item][capacity] = [max_val_without_current, max_val_with_current].max
    }
}



puts 

mat.each do |row|
    print row
    puts
end

puts
