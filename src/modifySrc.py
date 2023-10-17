import os
import re

def process_java_code(filename, java_code, attributes_to_change):
    # Add "this." before the lowercased attribute if it's being assigned 
    # and has no leading "this." and is at the beginning of a line.
    for attr in attributes_to_change:
        pattern_assignment = r'(?m)^\s*(?<!this\.)\b' + re.escape(attr) + r'\s*='
        replacement = lambda match: match[0][:len(match[0]) - len(match[0].lstrip())] + 'this.' + attr.lower() + ' ='
        if filename == 'EnrollmentSystem.java':
            replacement = lambda match: match[0][:len(match[0]) - len(match[0].lstrip())] + attr.lower() + ' ='
        java_code = re.sub(pattern_assignment, replacement, java_code, flags=re.IGNORECASE)

    # Convert private to public
    java_code = java_code.replace('private', 'public')
    java_code = java_code.replace('protected', 'public')

    # Handle attributes declaration in the class
    for attr in attributes_to_change:
        java_code = re.sub(r'\b' + re.escape(attr) + r'\b', attr.lower(), java_code, flags=re.IGNORECASE)

    return java_code

def process_directory(directory_path, attributes_to_change):
    # List all files in the directory
    for filename in os.listdir(directory_path):
        if filename.endswith('.java'):
            filepath = os.path.join(directory_path, filename)
            
            # Read the content of the file
            with open(filepath, 'r') as file:
                content = file.read()
            
            # Process the content
            modified_content = process_java_code(filename, content, attributes_to_change)
            
            # Overwrite the original file with the modified content
            with open(filepath, 'w') as file:
                file.write(modified_content)
            
            print(f"Processed: {filepath}")

# List of attributes you want to change
attributes_to_change = ["StudentID", "Department",
                        "YearOfStudy", "CGA", "Preferences", "CompletedCourses", 
                        "isHonorsCourse", "prerequisites", "courseCode", 
                        "department", "capacity", "enrolledStudents", "waitlist", "Students", "Courses"]

# Directory path where your Java source files are located
directory_path = './main/java/hk/ust/comp3021/'
process_directory(directory_path, attributes_to_change)