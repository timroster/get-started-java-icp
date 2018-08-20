/*
 * Copyright IBM Corp. 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wasdev.sample.store;

import java.util.Collection;


import wasdev.sample.Visitor;

/**
 * Defines the API for a ToDo store.
 *
 */
public interface VisitorStore {

	/**
	 * Gets all Visitors from the store.
	 * 
	 * @return All Visitors.
	 * @throws Exception
	 */
	public Collection<Visitor> getAll();


	/**
	 * Persists a Visitor to the store.
	 * 
	 * @param td
	 *            The Visitor  to persist.
	 * @return The persisted ToDo. The ToDo will not have a unique ID..
	 */
	public Visitor persist(Visitor vi);


	
	/**
	 * Counts the number of Visitors
	 * 
	 * @return The total number of Visitors.
	 * @throws Exception
	 */
	public int count() throws Exception;
}
